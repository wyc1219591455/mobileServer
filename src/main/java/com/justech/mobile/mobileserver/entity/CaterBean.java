package com.justech.mobile.mobileserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * @author clay
 * @version 1.0
 * @date 2019/7/31 16:51
 * @Description 报餐记录
 */

public class CaterBean {

    /**
     * 报餐日期
     */
    @JsonProperty("date")
    private String date;

    /**
     * 报餐情况
     */
    @JsonProperty("report")
    private String[] report;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getReport() {
        return report;
    }

    public void setReport(String[] report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "CaterBean{" +
                "date='" + date + '\'' +
                ", report=" + Arrays.toString(report) +
                '}';
    }
}
