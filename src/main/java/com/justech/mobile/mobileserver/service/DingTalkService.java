//package com.justech.mobile.mobileserver.service;
//
//import com.dingtalk.api.DefaultDingTalkClient;
//import com.dingtalk.api.DingTalkClient;
//import com.dingtalk.api.request.OapiUserGetRequest;
//import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
//import com.dingtalk.api.response.OapiUserGetResponse;
//import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
//import com.justech.mobile.mobileserver.entity.Account;
//import com.justech.mobile.mobileserver.repository.AccountRepostitory;
//import com.justech.mobile.mobileserver.util.AccessTokenUtil;
//import com.taobao.api.ApiException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * clay
// * 钉钉 授权服务
// */
//@Service
//public class DingTalkService {
//
//    public OapiUserGetResponse grant(String appid ,String appSecret,String authCode){
//        //获取accessToken,注意正是代码要有异常流处理
//        String accessToken = AccessTokenUtil.getToken(appid,appSecret);
//        //获取用户信息
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
//        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
//        request.setCode(authCode);
//        request.setHttpMethod("GET");
//        OapiUserGetuserinfoResponse response;
//        try {
//            response = client.execute(request, accessToken);
//        } catch (ApiException e) {
//            e.printStackTrace();
//            return null;
//        }
//        //3.查询得到当前用户的userId
//        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
//        String userId = response.getUserid();
//        OapiUserGetResponse oapiUserGetResponse = getUserInfo(accessToken, userId);
//        return oapiUserGetResponse;
//    }
//
//    //获取用户信息
//    public OapiUserGetResponse getUserInfo(String accessToken, String userId) {
//        try {
//            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
//            OapiUserGetRequest request = new OapiUserGetRequest();
//            request.setUserid(userId);
//            request.setHttpMethod("GET");
//            OapiUserGetResponse response = client.execute(request, accessToken);
//            return response;
//        } catch (ApiException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//}
