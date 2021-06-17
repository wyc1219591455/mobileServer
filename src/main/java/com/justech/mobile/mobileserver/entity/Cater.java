package com.justech.mobile.mobileserver.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 * @date 2019/7/31 16:51
 * @Description mobile-server
 */

public class Cater {

    /**
     * 报餐记录
     */
    @JsonProperty("records")
    private List<CaterBean> records;

    /**
     * 报餐异常记录
     */
    @JsonProperty("exceptionRecords")
    private List<CaterExPro> exceptionRecords;

    @Override
    public String toString() {
        return "Cater{" +
                "records=" + records +
                ", exceptionRecords=" + exceptionRecords +
                '}';
    }

    public List<CaterBean> getRecords() {
        return records;
    }

    public void setRecords(List<CaterBean> records) {
        this.records = records;
    }

    public List<CaterExPro> getExceptionRecords() {
        return exceptionRecords;
    }

    public void setExceptionRecords(List<CaterExPro> exceptionRecords) {
        this.exceptionRecords = exceptionRecords;
    }



}
