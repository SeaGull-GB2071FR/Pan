package com.GB2071FR.Pan.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author GB2071FR
 * @since 2023-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
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
     * qqOpenId
     */
    private String qqOpenId;

    /**
     * qq头像
     */
    private String qqAvatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 加入时间
     */
    private LocalDateTime joinTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 0:禁用 1:启用
     */
    private Boolean status;

    /**
     * 使用空间单位byte
     */
    private Long userSpace;

    /**
     * 总空间
     */
    private Long totalSpace;


}
