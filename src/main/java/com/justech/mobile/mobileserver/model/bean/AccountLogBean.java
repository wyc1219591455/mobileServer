package com.justech.mobile.mobileserver.model.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author clay
 * @version 1.0
 * @date 2019/9/5 14:59
 * @Description 账户操作日志对象 复刻
 */

@Data
public class AccountLogBean {

    private String id;

    /**
     * 事件id
     */
    private String eventId;

    /**
     * 手机时区
     */
    private String timezone;

    /**
     * 发生时间戳
     */
    private Date createTime;

    /**
     * 手机IP
     */
    private String ipAddress;


    /**
     * 设备型号 （安卓：android,苹果：ios）
     */
    private String deviceType;

    /**
     * 手机型号 （iphone）
     */
    private String deviceModel;

    /**
     * 手机系统
     */
    private String phoneSystem;

    /**
     * 系统版本
     */
    private String systemVersion;

    /**
     * 系统语言
     */
    private String systemLanguage;

    /**
     * APP名称
     */
    private String appName;

    /**
     * app版本
     */
    private String appVersion;

    /**
     * app渠道
     */
    private String appChannel;

    /**
     * 手机设备唯一号
     */
    private String deviceId;

    /**
     * 网络
     */
    private String internet;

    /**
     * SSID
     */
    private String ssid;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 板块名称
     */
    private String name;

}
