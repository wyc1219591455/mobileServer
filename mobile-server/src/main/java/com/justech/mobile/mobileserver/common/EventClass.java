package com.justech.mobile.mobileserver.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author clay
 * @version 1.0
 * @date 2019/9/5 17:05
 * @Description 事件常量
 */

public class EventClass {

    public static Map<String,String> map ;
    public static final  String EVENT_APP_START = "APP_START";
    public static final  String EVENT_APP_FOREGROUND = "APP_FOREGROUND";
    public static final  String EVENT_APP_LOGIN = "APP_LOGIN";
    public static final  String EVENT_APP_LOGOUT = "APP_LOGOUT";
    public static final  String EVENT_APP_GETSMS = "APP_GETSMS";
    public static final  String EVENT_APP_INDEX_APP_CLICK = "INDEX_APP_CLICK";
    public static final  String EVENT_APP_MARKET_APP_CLICK = "MARKET_APP_CLICK";
    public static final  String EVENT_APP_INDEX_MARKET_CLICK = "INDEX_MARKET_CLICK";
    public static final  String EVENT_APP_INDEX_NOTICE_CLICK = "INDEX_NOTICE_CLICK";
    public static final  String EVENT_APP_INDEX_PERSON_CLICK = "INDEX_PERSON_CLICK";
    static {
        map = new HashMap<>();
        map.put(EVENT_APP_START,"启动");
        map.put(EVENT_APP_FOREGROUND,"唤醒");
        map.put(EVENT_APP_LOGIN,"登录");
        map.put(EVENT_APP_LOGOUT,"注销");
        map.put(EVENT_APP_GETSMS,"发送验证码");
        map.put(EVENT_APP_INDEX_APP_CLICK,"点击首页功能");
        map.put(EVENT_APP_MARKET_APP_CLICK,"点击更多功能");
        map.put(EVENT_APP_INDEX_MARKET_CLICK,"点击全部应用");
        map.put(EVENT_APP_INDEX_NOTICE_CLICK,"点击消息中心");
        map.put(EVENT_APP_INDEX_PERSON_CLICK,"点击设置");
    }


}
