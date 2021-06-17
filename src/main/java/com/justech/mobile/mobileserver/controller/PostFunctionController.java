package com.justech.mobile.mobileserver.controller;

import com.justech.mobile.mobileserver.To.FunctionTreeTo;
import com.justech.mobile.mobileserver.To.PostFunctionInfoTo;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.entity.PostFunctionType;
import com.justech.mobile.mobileserver.entity.PostFunctionUser;
import com.justech.mobile.mobileserver.service.PostFunctionService;
import com.justech.mobile.mobileserver.util.AesUtil;
import com.justech.mobile.mobileserver.util.CumstonException;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AesUtil aesUtil;

    @GetMapping(value = "/try", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object test() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return aesUtil.encrypt("1123564");
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAll() {
        return postFunctionService.getAllFunctionInfo();
    }

    @PostMapping(value = "/type", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addType(@RequestBody PostFunctionType postFunctionType) {

        try {
            return new Response().of(ResultEnum.SUCCESS, postFunctionService.addType(postFunctionType));
        } catch (CumstonException e) {
            return new Response().of(ResultEnum.PARAMS_VALID_ERR, e.getMessage());
        }
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addUser(@RequestBody PostFunctionUser postFunctionUser) {
        try {
            return new Response().of(ResultEnum.SUCCESS, postFunctionService.addUser(postFunctionUser));
        } catch (CumstonException e) {
            return new Response().of(ResultEnum.PARAMS_VALID_ERR, e.getMessage());
        }
    }

    @GetMapping(value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FunctionTreeTo> adminAll() {
        return postFunctionService.adminAll();
    }

    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUser(@PathVariable("id") Integer id) {
        postFunctionService.deleteUser(id);
        return new Response().of(ResultEnum.SUCCESS, null);
    }

    @DeleteMapping(value = "/type/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteType(@PathVariable("id") Integer id) {
        try {
            postFunctionService.deleteType(id);
            return new Response().of(ResultEnum.SUCCESS, null);
        } catch (CumstonException e) {
            return new Response().of(ResultEnum.BAD, e.getMessage(), null);
        }

    }

    @PutMapping(value = "/type/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updatType(@PathVariable("id") Integer id, @RequestBody PostFunctionType postFunctionType) {
        PostFunctionType type = postFunctionService.getOneBy(id);
        if (type == null)
            return new Response().of(ResultEnum.INVALIDPARAM, null);
        type.setName(StringUtils.isEmpty(postFunctionType.getName()) ? type.getName() : postFunctionType.getName());
        type.setSort(StringUtils.isEmpty(postFunctionType.getSort()) ? type.getSort() : postFunctionType.getSort());
        return new Response().of(ResultEnum.SUCCESS, postFunctionService.update(type));
    }

    @PutMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updatUser(@PathVariable("id") Integer id, @RequestBody PostFunctionUser postFunctionUser) {
        PostFunctionUser user = postFunctionService.getOneById(id);
        if (user == null)
            return new Response().of(ResultEnum.INVALIDPARAM, null);
        user.setFunName(StringUtils.isEmpty(postFunctionUser.getFunName()) ? user.getFunName() : postFunctionUser.getFunName());
        user.setEmpNo(StringUtils.isEmpty(postFunctionUser.getEmpNo()) ? user.getEmpNo() : postFunctionUser.getEmpNo());
        user.setSort(StringUtils.isEmpty(postFunctionUser.getSort()) ? user.getSort() : postFunctionUser.getSort());
        try {
            return new Response().of(ResultEnum.SUCCESS, postFunctionService.update(user));
        } catch (CumstonException e) {
            return new Response().of(ResultEnum.BAD, e.getMessage(), null);
        }
    }

}
