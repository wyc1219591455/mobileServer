package com.justech.mobile.mobileserver.controller;

import com.justech.mobile.mobileserver.To.OrganizationTo;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.service.OrganizationService;
import com.justech.mobile.mobileserver.util.OrganizationEnum;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 12:14 2019/10/22
 * @Description : TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @GetMapping(value = "/top",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getOrg(){
        List list=organizationService.getAllOrganization();
        return new Response().of(ResultEnum.SUCCESS,list);
    }
    @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getOrgTo(@RequestParam("type") String type,@RequestParam("id") String id){
        OrganizationTo organizationTo=null;
        if ("ORG".equals(type)){
            organizationTo=organizationService.getAllOrganization(OrganizationEnum.ORG,id);
        }
        if ("DEPT".equals(type)){
            organizationTo=organizationService.getAllOrganization(OrganizationEnum.DEPT,id);
        }
        return new Response().of(ResultEnum.SUCCESS,organizationTo);
    }


}
