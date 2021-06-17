package com.justech.mobile.mobileserver.common;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author clay
 * @version 1.0
 * @date 2019/9/10 9:03
 * @Description handle  conflict
 */


@Configuration
public class ElasticConfig {
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
