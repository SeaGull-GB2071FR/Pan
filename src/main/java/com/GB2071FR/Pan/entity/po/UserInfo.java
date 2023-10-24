package com.GB2071FR.Pan.entity.po;

import com.GB2071FR.Pan.entity.enums.DateTimePatternEnum;
import com.GB2071FR.Pan.utils.DateUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 用户信息
 */
@Data
@TableName("user_info")
public class  UserInfo implements Serializable {


    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private String userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq 头像
     */
    private String qqAvatar;

    /**
     * qq openID
     */
    private String qqOpenId;

    /**
     * 密码
     */
    private String password;

    /**
     * 加入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinTime;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /**
     * 0:禁用 1:正常
     */
    private Integer status;

    /**
     * 使用空间单位byte
     */
    private Long useSpace;

    /**
     * 总空间单位byte
     */
    private Long totalSpace;


    @Override
    public String toString() {
        return "用户ID:" + (userId == null ? "空" : userId) + "，昵称:" + (nickName == null ? "空" : nickName) + "，邮箱:" + (email == null ? "空" : email) + "，qqAvatar:" + (qqAvatar == null ? "空" : qqAvatar) + "，qqOpenId:" + (qqOpenId == null ? "空" : qqOpenId) + "，密码:" + (password == null ? "空" : password) + "，加入时间:" + (joinTime == null ? "空" : DateUtil.format(joinTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "，最后登录时间:" + (lastLoginTime == null ? "空" : DateUtil.format(lastLoginTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "，0:禁用 1:正常:" + (status == null ? "空" : status) + "，useSpace:" + (useSpace == null ? "空" : useSpace) + "，totalSpace:" + (totalSpace == null ? "空" : totalSpace);
    }
}
