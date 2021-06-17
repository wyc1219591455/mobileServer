package com.justech.mobile.mobileserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.elasticsearch.dao.AssetsDao;
import com.justech.mobile.mobileserver.elasticsearch.entity.AssetsEntity;
import com.justech.mobile.mobileserver.util.RSAUtil;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName AssetsController
 * @Author: xiaofeng.yang
 * @Date: Create in 16:09 2019/10/9
 * @Description : TODO
 * @Version: 1.0
 */
@RestController
public class AssetsController {
    @Autowired
    private AssetsDao assetsDao;
    @Value("${rsa.privateKey}")
    private String rsaPrivateKey;

    @GetMapping(value = "/assets", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<AssetsEntity>> getMineAssets(@RequestHeader(name = "Authorization") String authorization) {
        String token = null;
        try {
            token = RSAUtil.rsaDecrypt(authorization, rsaPrivateKey);
            JSONObject jsonObject = JSONObject.parseObject(token);
            String empNo = jsonObject.getString("empNo");
            List<AssetsEntity> list=   assetsDao.findAllByKeeperID(empNo);
            Set set=new HashSet();
            set.addAll(list);
            List<AssetsEntity> newlist=new ArrayList<>();
            newlist.addAll(set);
            return new Response<List<AssetsEntity>>().of(ResultEnum.SUCCESS,newlist);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<List<AssetsEntity>>().of(ResultEnum.BAD, null);
        }

    }
}
