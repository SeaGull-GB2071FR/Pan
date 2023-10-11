package com.easypan.entity.dto;

import java.io.Serializable;

public class UserSpaceDto implements Serializable {
    public Long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Long getUserSpace() {
        return userSpace;
    }

    public void setUserSpace(Long userSpace) {
        this.userSpace = userSpace;
    }

    private Long userSpace;
    private Long totalSpace;
}
