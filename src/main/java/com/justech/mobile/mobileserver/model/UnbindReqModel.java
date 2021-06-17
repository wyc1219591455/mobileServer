package com.justech.mobile.mobileserver.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author clay
 * @version 1.0
 * @date 2019/8/2 12:17
 * @Description 解绑请求对象
 */
@Data
public class UnbindReqModel {

    @NotNull(message = "empNo 不能为空")
    private String empNo;


    private String type = "dingTalk";

}
