package com.GB2071FR.Pan.service.impl;

import com.GB2071FR.Pan.component.RedisComponent;
import com.GB2071FR.Pan.entity.config.AppConfig;
import com.GB2071FR.Pan.entity.constants.Constants;
import com.GB2071FR.Pan.entity.dto.SysSettingsDto;
import com.GB2071FR.Pan.entity.po.EmailCode;
import com.GB2071FR.Pan.entity.po.UserInfo;
import com.GB2071FR.Pan.exception.BusinessException;
import com.GB2071FR.Pan.mapper.EmailCodeMapper;
import com.GB2071FR.Pan.mapper.UserInfoMapper;
import com.GB2071FR.Pan.service.EmailCodeService;
import com.GB2071FR.Pan.utils.StringTools;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class EmailCodeServiceImpl extends ServiceImpl<EmailCodeMapper, EmailCode> implements EmailCodeService {

    @Autowired
    private EmailCodeMapper emailCodeMapper;


    @Autowired
    private UserInfoMapper userInfoMapper;

    //      发送邮箱
    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RedisComponent redisComponent;

    private static final Logger logger = LoggerFactory.getLogger(EmailCodeServiceImpl.class);


    //验证邮箱验证码
    @Override
    public void checkCode(String email, String Code) {
        EmailCode emailCode = baseMapper.selectByEmailAndCode(email, Code);
        if (emailCode == null) {
            throw new BusinessException("邮箱验证码不正确");
        }
        if (emailCode.getStatus() == 1 || System.currentTimeMillis() - emailCode.getCreateTime().getTime() > Constants.LENGTH_15 * 1000 * 60) {
            throw new BusinessException("邮箱验证码已经失效");
        }

        emailCodeMapper.disableEmailCode(email);

    }

    //    发送邮箱验证码
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendEmailCode(String email, Integer type) {
        if (type == Constants.ZERO) {
            UserInfo userInfo = userInfoMapper.selectByEmail(email);
            if (userInfo != null)
                throw new BusinessException("邮箱已经存在");
        }

        String code = StringTools.getRandomNumber(Constants.LENGTH_5);
        System.out.println(code);
        //        发送验证码

        sendEmailCode(email, code);

        //      多次验证码重置,将之前的验证码置为无效
        emailCodeMapper.disableEmailCode(email);

        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        emailCode.setStatus(Constants.ZERO);
        emailCode.setCreateTime(new Date());
        baseMapper.insert(emailCode);


    }


    //    方法重载
    private void sendEmailCode(String toEmail, String code) {
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
            helper.setFrom(appConfig.getSendUserName());
            helper.setTo(toEmail);

            /**
             * 获取redis里面的标题，验证码，内容
             */
            SysSettingsDto sysSettingDto = redisComponent.getSysSettingDto();

            helper.setSubject(sysSettingDto.getRegisterEmailTitle());
            helper.setText(String.format(sysSettingDto.getRegisterEmailContent(), code));
            helper.setSentDate(new Date());
            javaMailSender.send(mimeMailMessage);

        } catch (Exception e) {
            logger.error("邮件发送失败", e);
            throw new BusinessException("邮件发送失败");
        }
    }

}
