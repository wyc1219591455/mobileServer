package com.justech.mobile.mobileserver.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author clay
 * @version 1.0
 * @date 2019/8/2 12:17
 * @Description 报餐接口请求对象
 */
@Data
public class AccountLogReqModel {


    private String deviceId;

    private String userId;

    private String beginTime;

    private String endTime;

    private String eventId;

    private Integer pageNo = 0;

    private Integer pageSize = 10;
}
