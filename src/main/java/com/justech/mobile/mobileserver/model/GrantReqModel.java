package com.justech.mobile.mobileserver.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author clay
 * @version 1.0
 * @date 2019/8/2 12:17
 * @Description 钉钉授权请求对象
 */
@Data
public class GrantReqModel {

    @NotNull(message = "empNo 不能为空")
    private String empNo;

    @NotNull(message = "authCode 不能为空")
    private String authCode;

    @NotNull(message = "deviceType 不能为空")
    private String deviceType;

    private String type = "dingTalk";


}
