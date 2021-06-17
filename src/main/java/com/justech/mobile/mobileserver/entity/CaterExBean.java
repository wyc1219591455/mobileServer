package com.justech.mobile.mobileserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * @author clay
 * @version 1.0
 * @date 2019/7/31 16:51
 * @Description 报餐异常类
 */

public class CaterExBean {


    /**
     * 日期
     */
    @JsonProperty("date")
    private String date;

    /**
     * 异常原因
     */
    @JsonProperty("reason")
    private String reason;

    /**
     * 类型
     */
    @JsonProperty("type")
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "CaterExBean{" +
                "date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
