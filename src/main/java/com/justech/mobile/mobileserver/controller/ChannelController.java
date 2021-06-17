package com.justech.mobile.mobileserver.controller;

import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.entity.ChannelAndCategory;
import com.justech.mobile.mobileserver.service.CategoriesService;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ChannelController
 * @Author: xiaofeng.yang
 * @Date: Create in 9:29 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/channels")
public class ChannelController {
    @Autowired
    private CategoriesService categoriesService;
    /*
     * @Name getCategories
     * @Author xiaofeng.yang
     * @Description 获取分类列表
     * @Date 13:50 2019/7/9
     * @Param [name, level]
     * @return java.lang.Object
     **/
    @RequestMapping(value = "/categories",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response<List<ChannelAndCategory>> getCategories(@RequestParam("name") String name){

        Response response= new Response().of(ResultEnum.SUCCESS,categoriesService.getCategories(name,1));
        return response;
    }
    @RequestMapping(value = "/categories",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response getCategoriesByDto(@RequestBody ChannelAndCategory channelAndCategory){
        List list=categoriesService.getCategoriesAll(channelAndCategory);
        return new Response().of(ResultEnum.SUCCESS,list);
    }
}
