package com.justech.mobile.mobileserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 * @date 2019/7/31 16:51
 * @Description 报餐异常
 */

public class CaterExPro {


    /**
     * 日期
     */
    @JsonProperty("date")
    private String date;

    /**
     * 异常纪录
     */
    @JsonProperty("caterExBeans")
    private List<CaterExBean> caterExBeans;


    @Override
    public String toString() {
        return "CaterExPro{" +
                "date='" + date + '\'' +
                ", caterExBeans=" + caterExBeans +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CaterExBean> getCaterExBeans() {
        return caterExBeans;
    }

    public void setCaterExBeans(List<CaterExBean> caterExBeans) {
        this.caterExBeans = caterExBeans;
    }





}
