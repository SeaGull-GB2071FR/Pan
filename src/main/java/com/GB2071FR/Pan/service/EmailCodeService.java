package com.GB2071FR.Pan.service;

import com.GB2071FR.Pan.entity.po.EmailCode;
import com.baomidou.mybatisplus.extension.service.IService;

public interface EmailCodeService extends IService<EmailCode> {


//    发送邮箱验证码
    void sendEmailCode(String email,Integer type);

//    验证邮箱验证码
    void checkCode(String email,String emailCode);
}
