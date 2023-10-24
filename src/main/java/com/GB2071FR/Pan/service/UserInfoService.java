package com.GB2071FR.Pan.service;

import com.GB2071FR.Pan.entity.dto.SessionWebUserDto;
import com.GB2071FR.Pan.entity.po.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GB2071FR
 * @since 2023-10-10
 */
public interface UserInfoService extends IService<UserInfo> {


    void register(String email, String nickName, String password, String emailCode);

    SessionWebUserDto login(String email,String password);
}
