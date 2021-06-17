package com.justech.mobile.mobileserver.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName GlobalErrorHandler
 * @Author: xiaofeng.yang
 * @Date: Create in 14:08 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
//@Controller
//@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class GlobalErrorHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }
}
