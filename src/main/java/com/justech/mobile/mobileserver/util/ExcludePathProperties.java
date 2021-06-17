package com.justech.mobile.mobileserver.util;

import com.justech.mobile.mobileserver.To.ExcludePathDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ExcludePathDto
 * @Author: xiaofeng.yang
 * @Date: Create in 8:35 2019/7/29
 * @Description : TODO
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "cumstonfilter")
public class ExcludePathProperties {

    private ExcludePathDto[] exclude;

    public List<ExcludePathDto> getExclude() {
        return Arrays.asList(exclude);
    }

    public void setExclude(ExcludePathDto[] exclude) {
        this.exclude = exclude;
    }
}
