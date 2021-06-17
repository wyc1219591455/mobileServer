package com.justech.mobile.mobileserver.controller;

import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.service.ApplicationService;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName ApplicationController
 * @Author: xiaofeng.yang
 * @Date: Create in 10:33 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
@RestController
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    /*
     * @Name getApplicationList
     * @Author xiaofeng.yang
     * @Description 获取应用列表
     * @Date 10:39 2019/7/16
     * @Param [id]
     * @return com.justech.mobile.mobileserver.common.Response
     **/
    @RequestMapping(value = "/block/{id}/application",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getApplicationList(@PathVariable("id") int id){

        return new Response().of(ResultEnum.SUCCESS,applicationService.getApplicationList(id));
    }
    /*
     * @Name getBlock
     * @Author xiaofeng.yang
     * @Description //TODO
     * @Date 12:08 2019/7/16
     * @Param [name]
     * @return com.justech.mobile.mobileserver.common.Response
     **/
    @RequestMapping(value = "/block",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getBlock(@RequestParam("name") String name){
          return new Response().of(ResultEnum.SUCCESS,applicationService.getBlock(name));
    }

}
