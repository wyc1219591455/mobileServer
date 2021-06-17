package com.justech.mobile.mobileserver.properties;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName SMSProperties
 * @Author: xiaofeng.yang
 * @Date: Create in 14:56 2019/8/9
 * @Description : TODO
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "sms")
@Getter
@Setter
@RequiredArgsConstructor
public class SMSProperties {
    private String url;
    private  String account;
    private String password;
    private String mould;
    private int expire;
}
