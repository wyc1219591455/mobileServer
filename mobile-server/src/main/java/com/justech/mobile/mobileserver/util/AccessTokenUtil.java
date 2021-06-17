//package com.justech.mobile.mobileserver.util;
//
//import com.dingtalk.api.DefaultDingTalkClient;
//import com.dingtalk.api.request.OapiGettokenRequest;
//import com.dingtalk.api.response.OapiGettokenResponse;
//import com.taobao.api.ApiException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 获取access_token工具类
// */
//public class AccessTokenUtil {
//    private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);
//
//    public static String getToken(String apiId, String apiSecret) throws RuntimeException {
//        try {
//            DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
//            OapiGettokenRequest request = new OapiGettokenRequest();
//
//            request.setAppkey(apiId);
//            request.setAppsecret(apiSecret);
//            request.setHttpMethod("GET");
//            OapiGettokenResponse response = client.execute(request);
//            String accessToken = response.getAccessToken();
//            return accessToken;
//        } catch (ApiException e) {
//            bizLogger.error("getAccessToken failed", e);
//            throw new RuntimeException();
//        }
//
//    }
//
//}
