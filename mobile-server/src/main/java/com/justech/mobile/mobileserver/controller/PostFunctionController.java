package com.justech.mobile.mobileserver.controller;

import com.justech.mobile.mobileserver.service.PostFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName PostFunctionController
 * @Author: xiaofeng.yang
 * @Date: Create in 10:08 2019/10/12
 * @Description : TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/postFunction")
public class PostFunctionController {
    @Autowired
    private PostFunctionService postFunctionService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    // 返回数据
    public Object getAll() {
        return postFunctionService.getAllFunctionInfo();
    }

}
