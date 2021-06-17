package com.justech.mobile.mobileserver.model.bean;

import lombok.Data;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 * @date 2019/9/11 15:24
 * @Description log 相关统计数据
 */
@Data
public class StatisticBean {

    private int iosAmount;

    private int androidAmount;

    private int deviceAmount;

    private int userAmmount;

    private int onlineUserAmount;

    private String androidPercent;

    private String iosPercent;

}
