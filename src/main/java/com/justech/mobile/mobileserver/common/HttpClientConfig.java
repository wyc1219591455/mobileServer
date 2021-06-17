package com.justech.mobile.mobileserver.common;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @ClassName HttpClientConfig
 * @Author: xiaofeng.yang
 * @Date: Create in 10:26 2019/7/10
 * @Description : TODO
 * @Version: 1.0
 */
@Configuration
public class HttpClientConfig {
    @Value("${http.maxTotal}")
    private Integer maxTotal;
    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;
    @Value("${http.connectTimeout}")
    private Integer connectTimeout;
    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;
    @Value("${http.socketTimeout}")
    private Integer socketTimeout;
    @Value("${http.validateAfterInactivity}")
    private int validateAfterInactivity;
    /*
     * @Name getHttpClientConnectionManager
     * @Author xiaofeng.yang
     * @Description httpclient连接池
     * @Date 10:34 2019/7/10
     * @Param []
     * @return org.apache.http.impl.conn.PoolingHttpClientConnectionManager
     **/
    @Bean("httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(maxTotal);
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        httpClientConnectionManager.setValidateAfterInactivity(validateAfterInactivity);
        return httpClientConnectionManager;
    }
  /*
   * @Name getHttpClientBuilder
   * @Author xiaofeng.yang
   * @Description HttpClient工厂
   * @Date 10:37 2019/7/10
   * @Param [httpClientConnectionManager]
   * @return org.apache.http.impl.client.HttpClientBuilder
   **/
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        return httpClientBuilder;
    }

   /*
    * @Name getCloseableHttpClient
    * @Author xiaofeng.yang
    * @Description 注入连接池
    * @Date 10:38 2019/7/10
    * @Param [httpClientBuilder]
    * @return org.apache.http.impl.client.CloseableHttpClient
    **/
//    @Bean
    @Deprecated
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }
    /*
     * @Name getBuilder
     * @Author xiaofeng.yang
     * @Description requestConfig的工厂
     * @Date 10:38 2019/7/10
     * @Param []
     * @return org.apache.http.client.config.RequestConfig.Builder
     **/
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout);
    }
    /*
     * @Name getRequestConfig
     * @Author xiaofeng.yang
     * @Description RequestConfig对象
     * @Date 10:38 2019/7/10
     * @Param [builder]
     * @return org.apache.http.client.config.RequestConfig
     **/
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }


}
