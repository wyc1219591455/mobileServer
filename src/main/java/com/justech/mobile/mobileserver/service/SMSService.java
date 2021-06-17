package com.justech.mobile.mobileserver.service;

import com.alibaba.fastjson.JSONObject;
import com.justech.mobile.mobileserver.To.SMSDto;
import com.justech.mobile.mobileserver.To.SMSResDto;
import com.justech.mobile.mobileserver.entity.JustechUser;
import com.justech.mobile.mobileserver.properties.SMSProperties;
import com.justech.mobile.mobileserver.repository.JustechUserRepository;
import com.justech.mobile.mobileserver.util.CumstonException;
import com.justech.mobile.mobileserver.util.HttpUtil;
import com.justech.mobile.mobileserver.util.RedisUtils;
import com.justech.mobile.mobileserver.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SMSService
 * @Author: xiaofeng.yang
 * @Date: Create in 15:20 2019/8/9
 * @Description : TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class SMSService {
    @Autowired
    private SMSProperties smsProperties;

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private JustechUserRepository justechUserRepository;

    /*
     * @Name sendSMS
     * @Author xiaofeng.yang
     * @Description 短信验证码发送
     * @Date 8:49 2019/8/12
     * @Param [phone]
     * @return void
     **/
    public void sendSMS(String phone) throws CumstonException {
        if (StringUtils.isBlank(phone)) {
            throw new CumstonException("phone number can not be empty !");
        }
        String expireKey = new StringBuffer("message|expire").append(":").append(phone).toString();//短信验证码 缓存key
        String interValKey = new StringBuffer("message|sendInterval").append(":").append(phone).toString();//短息发送一分钟间隔 缓存key
        String value = redisTemplate.opsForValue().get(interValKey);
        if (StringUtils.isNotBlank(value)) { // 一分钟内只允许发送一次短信
            throw new CumstonException(ResultEnum.TOO_MANY_REQUEST.getMsg());
        }

        JustechUser justechUser=justechUserRepository.findTopByUserMobileAndUserStatus(phone,"1");
        if (justechUser == null||!"1".equals(justechUser.getUserStatus())) {
            log.error(ResultEnum.USER_NOT_FIND.getMsg() + "|" + "手机号：" + phone);
            throw new CumstonException(ResultEnum.USER_NOT_FIND.getMsg());
        }
        String random = random();
        String msg = getMsg(smsProperties.getMould(), random, String.valueOf(smsProperties.getExpire()));
        SMSDto smsDto = new SMSDto(msg, phone, smsProperties);
        HttpPost httpPost = new HttpPost(smsProperties.getUrl());
        StringEntity entity = new StringEntity(JSONObject.toJSONString(smsDto), "utf8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        entity.setContentEncoding("UTF-8");
        httpPost.setEntity(entity);
        try {
            CloseableHttpResponse response = httpUtil.doPost(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String restr = EntityUtils.toString(response.getEntity(), "UTF-8");
                SMSResDto smsResDto = JSONObject.parseObject(restr, SMSResDto.class);
                if ("0".equals(smsResDto.getCode())) {
                    redisTemplate.opsForValue().set(interValKey,"sendInterVal",60,TimeUnit.SECONDS);//加入一分钟限制
                    redisTemplate.opsForValue().set(expireKey, random, 300, TimeUnit.SECONDS);// 五分钟缓存
                    return;
                }
                log.error(phone + ":" + restr);
                throw new CumstonException("send SMS error:" + smsResDto.getErrorMsg());
            } else
                throw new CumstonException("send SMS error:" + response.getStatusLine().getStatusCode() + ":" + response.getEntity().getContent().toString());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("send SMS I/O error", e);
            throw new CumstonException("send SMS I/O error");
        } finally {
            httpPost.abort();
        }

    }

    /**
     * 验证手机短信
     *
     * @param phone
     * @return
     */
    public boolean validateSmsCode(String phone, String code) throws CumstonException {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) return false;
        String expireKey = new StringBuffer("message|expire").append(":").append(phone).toString();
        String sysCode = redisTemplate.opsForValue().get(expireKey);
        boolean flag = StringUtils.equalsIgnoreCase(sysCode, code) ? true : false;
        return flag;
    }


    private String getMsg(String msg, String... agrs) {
        return MessageFormat.format(msg, agrs);
    }

    /**
     * @return java.lang.String
     * @Name random
     * @Author xiaofeng.yang
     * @Description 随机生成4位数字验证码
     * @Date 8:50 2019/8/12
     * @Param []
     **/
    private String random() {
        Double a = (Math.random() * 9 + 1) * 1000;

        return String.valueOf(a.intValue());
    }

    /*
     * @Name setRedis
     * @Author xiaofeng.yang
     * @Description 存储验证码
     * @Date 8:52 2019/8/12
     * @Param [phone, ver]
     * @return void
     **/
    private void setRedis(String phone, String ver) {
        Map map = new HashMap();
        map.put("ver", ver);
        map.put("time", new Date().getTime());
        redisTemplate.opsForHash().putAll(phone, map);
        redisTemplate.expire(phone, 60 * 5, TimeUnit.SECONDS);

    }


}
