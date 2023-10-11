package com.easypan.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SysSettingsDto implements Serializable {
    private String registerEmailTitle = "邮箱验证码";

    private String registerEmailContent = "您好，您的邮箱验证码是:%s,15分钟有效。";

    private Integer userInitUseSpace = 5;

    public String getRegisterEmailTitle() {
        return registerEmailTitle;
    }

    public void setRegisterEmailTitle(String registerMailTitle) {
        this.registerEmailTitle = registerMailTitle;
    }

    public String getRegisterEmailContent() {
        return registerEmailContent;
    }

    public void setRegisterEmailContent(String registerEmailContent) {
        this.registerEmailContent = registerEmailContent;
    }

    public Integer getUserInitUseSpace() {
        return userInitUseSpace;
    }

    public void setUserInitUseSpace(Integer userInitUseSpace) {
        this.userInitUseSpace = userInitUseSpace;
    }
}
