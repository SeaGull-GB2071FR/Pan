package com.GB2071FR.Pan.entity.query;


import com.GB2071FR.Pan.entity.query.BaseParam;
import lombok.Data;

/**
 * 邮箱验证码参数
 */
@Data
public class EmailCodeQuery extends BaseParam {


    /**
     * 邮箱
     */
    private String email;

    private String emailFuzzy;

    /**
     * 编号
     */
    private String code;

    private String codeFuzzy;

    /**
     * 创建时间
     */
    private String createTime;

    private String createTimeStart;

    private String createTimeEnd;

    /**
     * 0:未使用  1:已使用
     */
    private Integer status;


}
