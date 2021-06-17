package com.justech.mobile.mobileserver.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author clay
 * @version 1.0
 * @date 2019/8/12 10:59
 * @Description 短信验证请求model
 */

@Data
public class SmsReqModel {

    @NotNull(message = "工号不能为空")
    private String  phone;

    @NotNull(message = "验证码不能为空")
    private String code;

}
