package com.GB2071FR.Pan.mapper;

import com.GB2071FR.Pan.entity.po.EmailCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface EmailCodeMapper extends BaseMapper<EmailCode> {

//    置旧验证码无效
    void disableEmailCode(@Param("email") String email);
}
