package com.GB2071FR.Pan.service.impl;

import com.GB2071FR.Pan.entity.constants.Constants;
import com.GB2071FR.Pan.entity.po.EmailCode;
import com.GB2071FR.Pan.entity.po.UserInfo;
import com.GB2071FR.Pan.exception.BusinessException;
import com.GB2071FR.Pan.mapper.EmailCodeMapper;
import com.GB2071FR.Pan.mapper.UserInfoMapper;
import com.GB2071FR.Pan.service.EmailCodeService;
import com.GB2071FR.Pan.service.UserInfoService;
import com.GB2071FR.Pan.utils.StringTools;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class EmailCodeServiceImpl extends ServiceImpl<EmailCodeMapper, EmailCode> implements EmailCodeService {

    @Autowired
    private EmailCodeMapper emailCodeMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

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
        //      TODO  发送验证码

        //      多次验证码重置,将之前的验证码置为无效
        emailCodeMapper.disableEmailCode(email);

        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        emailCode.setStatus(Constants.ZERO);
        emailCode.setCreateTime(new Date());
        emailCodeMapper.insert(emailCode);


    }
}
