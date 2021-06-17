package com.justech.mobile.mobileserver.common;

import brave.CurrentSpanCustomizer;
import brave.SpanCustomizer;
import brave.Tracing;
import brave.context.slf4j.MDCScopeDecorator;
import brave.http.HttpAdapter;
import brave.http.HttpClientParser;
import brave.http.HttpTracing;
import brave.httpclient.TracingHttpClientBuilder;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.ThreadLocalCurrentTraceContext;
import brave.servlet.TracingFilter;
import brave.spring.webmvc.SpanCustomizingAsyncHandlerInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import javax.servlet.Filter;

/**
 * @ClassName ZipkinConfig
 * @Author: xiaofeng.yang
 * @Date: Create in 16:23 2019/8/14
 * @Description : TODO
 * @Version: 1.0
 */
@Configuration
@Import(SpanCustomizingAsyncHandlerInterceptor.class)
public class ZipkinConfig implements WebMvcConfigurer {
    @Value("${zipkin.url}")
    private String zipkinBaseUrl;

    @Bean
    public Sender sender() {
        return OkHttpSender.create(zipkinBaseUrl);
    }
    @Bean
    public AsyncReporter<Span> spanReporter() {
        return AsyncReporter.create(sender());
    }


    /** Controls aspects of tracing such as the service name that shows up in the UI */
    @Bean
    Tracing tracing(@Value("${spring.application.name}") String serviceName) {
        return Tracing.newBuilder()
                .localServiceName(serviceName)
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder()
                        .addScopeDecorator(MDCScopeDecorator.create()) // puts trace IDs into logs
                        .build()
                )
                .spanReporter(spanReporter()).build();
    }

    /** Allows someone to add tags to a span if a trace is in progress */
    @Bean
    SpanCustomizer spanCustomizer(Tracing tracing) {
        return CurrentSpanCustomizer.create(tracing);
    }
    /** Decides how to name and tag spans. By default they are named the same as the http method */
    @Bean
    HttpTracing httpTracing(Tracing tracing) {
      return   HttpTracing.newBuilder(tracing).clientParser(new HttpClientParser(){
            @Override
            public <Req> void request(HttpAdapter<Req, ?> adapter, Req req, SpanCustomizer customizer) {
                customizer.name(this.spanName(adapter, req));
                String method = adapter.method(req);
                if (method != null) {
                    customizer.tag("http.method", method);
                }

                String path = adapter.path(req);
                if (path != null) {
                    customizer.tag("http.path", path);
                }
                String host= adapter.requestHeader(req,"Host");
                if (host != null) {
                    customizer.tag("http.host", host);
                }
            }
        }).build();

//        return HttpTracing.create(tracing);
    }
    /** Creates server spans for http requests */
    @Bean
    Filter tracingFilter(HttpTracing httpTracing) {
        return TracingFilter.create(httpTracing);
    }
    @Bean
    RestTemplateCustomizer useTracedHttpClient(HttpTracing httpTracing) {
        final CloseableHttpClient httpClient = TracingHttpClientBuilder.create(httpTracing).build();
        return new RestTemplateCustomizer() {
            @Override public void customize(RestTemplate restTemplate) {
                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
            }
        };
    }
    @Bean
    public CloseableHttpClient httpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder,HttpTracing httpTracing) {
        CloseableHttpClient httpClient = TracingHttpClientBuilder.create(httpTracing).build();
        return httpClient;
    }


    @Autowired
    SpanCustomizingAsyncHandlerInterceptor webMvcTracingCustomizer;

    /** Decorates server spans with application-defined web tags */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webMvcTracingCustomizer);
    }
}
