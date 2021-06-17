package com.justech.mobile.mobileserver.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName HttpUtil
 * @Author: xiaofeng.yang
 * @Date: Create in 11:11 2019/7/10
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class HttpUtil {
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;
    public CloseableHttpResponse doGet(HttpGet httpGet) throws IOException {
        httpGet.setConfig(requestConfig);
        return httpClient.execute(httpGet);
    }
    public CloseableHttpResponse doPost(HttpPost httpPost) throws IOException {
        httpPost.setConfig(requestConfig);
        return httpClient.execute(httpPost);
    }
}
