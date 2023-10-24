package com.GB2071FR.Pan.service.impl;

import com.GB2071FR.Pan.component.RedisComponent;
import com.GB2071FR.Pan.entity.config.AppConfig;
import com.GB2071FR.Pan.entity.constants.Constants;
import com.GB2071FR.Pan.entity.dto.SessionWebUserDto;
import com.GB2071FR.Pan.entity.dto.SysSettingsDto;
import com.GB2071FR.Pan.entity.dto.UserSpaceDto;
import com.GB2071FR.Pan.entity.enums.UserStatusEnum;
import com.GB2071FR.Pan.entity.po.UserInfo;
import com.GB2071FR.Pan.exception.BusinessException;
import com.GB2071FR.Pan.mapper.UserInfoMapper;
import com.GB2071FR.Pan.service.EmailCodeService;
import com.GB2071FR.Pan.service.UserInfoService;
import com.GB2071FR.Pan.utils.StringTools;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GB2071FR
 * @since 2023-10-10
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


    @Autowired
    private EmailCodeService emailCodeService;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private AppConfig appConfig;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String email, String nickName, String password, String emailCode) {
        UserInfo userInfo = baseMapper.selectByEmail(email);
        if (userInfo != null) {
            throw new BusinessException("邮箱账号已经存在");
        }
        userInfo = baseMapper.selectByNickName(nickName);
        if (userInfo != null) {
            throw new BusinessException("昵称已经存在");
        }
//      验证邮箱验证码
        emailCodeService.checkCode(email, emailCode);
        String userId = StringTools.getRandomNumber(Constants.LENGTH_10);
        userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setNickName(nickName);
        userInfo.setEmail(email);
        userInfo.setPassword(StringTools.encodeByMd5(password));
        userInfo.setStatus(UserStatusEnum.ENABLE.getStatus());
        userInfo.setUseSpace(0L);

        SysSettingsDto sysSettingsDto = redisComponent.getSysSettingDto();

        userInfo.setTotalSpace(sysSettingsDto.getUserInitUseSpace() * Constants.MB);

        baseMapper.insert(userInfo);

    }

    @Override
    public SessionWebUserDto login(String email, String password) {
        UserInfo userInfo = baseMapper.selectByEmail(email);
        if (userInfo == null || !userInfo.getPassword().equals(password)) {
            throw new BusinessException("账号或密码不正确");
        }

        if (UserStatusEnum.DISABLE.getStatus().equals(userInfo.getStatus()))
            throw new BusinessException("账号被禁用");

//        UserInfo updateInfo = new UserInfo();
        userInfo.setLastLoginTime(new Date());

        baseMapper.updateById(userInfo);

        SessionWebUserDto sessionWebUserDto = new SessionWebUserDto();
        sessionWebUserDto.setNickName(userInfo.getNickName());
        sessionWebUserDto.setUserId(userInfo.getUserId());

        if (ArrayUtils.contains(appConfig.getAdminEmails().split(","), email)) {
            sessionWebUserDto.setAdmin(true);
        } else {
            sessionWebUserDto.setAdmin(false);
        }
//        用户空间
        UserSpaceDto userSpaceDto = new UserSpaceDto();
//        userSpaceDto.setUserSpace();
        userSpaceDto.setTotalSpace(userInfo.getTotalSpace());
        redisComponent.saveUserSpaceUse(userInfo.getUserId(), userSpaceDto);

        return null;
    }
}
