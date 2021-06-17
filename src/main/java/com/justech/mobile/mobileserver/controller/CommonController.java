package com.justech.mobile.mobileserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.justech.mobile.mobileserver.common.BeanValidators;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.entity.JustechDept;
import com.justech.mobile.mobileserver.entity.JustechUser;
import com.justech.mobile.mobileserver.repository.JustechDeptRepository;
import com.justech.mobile.mobileserver.service.SMSService;
import com.justech.mobile.mobileserver.service.UserService;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;

/**
 * @author clay
 * @version 1.0
 * @date 2019/8/2 12:12
 * @Description 基础控制器, 编写一些公用的功能
 */

public class CommonController {


    @Autowired
    protected Validator validator;
    @Autowired
    public SMSService smsService;
    @Autowired
    public  StringRedisTemplate redisTemplate;
    @Autowired
    public UserService userService;
    @Autowired
    private JustechDeptRepository justechDeptRepository;
    /**
     * 服务端参数有效性验证
     *
     * @param response 错误信息（不能为null）
     * @param object   验证的实体对象
     * @param groups   验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 jsonMessage rows 中
     */
    protected boolean beanValidator(Response response, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            response.setCode(ResultEnum.PARAMS_VALID_ERR.getCode());
            response.setMsg(ResultEnum.PARAMS_VALID_ERR.getMsg());
            response.setData(list);
            return false;
        }
        return true;
    }

    public JustechDept findDeptById(String id){
        Optional<JustechDept> deptOptional=justechDeptRepository.findById(id);
        if (deptOptional.isPresent())
            return deptOptional.get();
        return null;
    }

    /**
     * 接口处理结果反馈 : ：API接口返回数据或交易处理结果的JSON处理
     *
     * @param describable
     * @return Response
     */
    public Response getResponse(ResultEnum describable) {
        Response response = new Response();
        response.setCode(describable.getCode());
        response.setMsg(describable.getMsg());
        return response;
    }

    /**
     * 接口处理结果反馈 : ：API接口返回数据或交易处理结果的JSON处理
     * @param code
     * @param msg
     * @return
     */
    public Response getResponse(int code,String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }



    /**
     * 取所有请求参数
     *
     * @param request
     * @return
     */
    protected Map<String, String> getParameters(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMap();
        Enumeration<String> parameterNames = request.getParameterNames();
        String parameterName;
        while (parameterNames.hasMoreElements()) {
            parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }
        return params;
    }


    public String queryUserNameByphone(String phone){
        JustechUser user=userService.findbyphonenum(phone);
        if (user==null)
            return null;
        return user.getEmpName();
    }

    public String queryUserNameByempno(String empno){
        JustechUser user=userService.findByEmpId(empno);
        if (user==null)
            return null;
        return user.getEmpName();
    }

    /**
     *
     * @param condition
     * @return
     */
    @Deprecated
    public String  queryUserNameInRedis(String condition){
        String pattern = '*' + condition + '*';//查找该手机号是否存在
        List<String> list = new ArrayList<>();

        Set<String> keySet = redisTemplate.keys(pattern);
        for (String key : keySet) {
            if (key.startsWith("user:")) {
                list.add(key);
            }
        }
        if (list.size() == 0) {
            return "";
        }

        Map user = redisTemplate.opsForHash().entries(list.get(0));
        if (user == null || user.isEmpty()) {
            return "";
        }
        String  userName = user.get("FEmpName").toString();
        return  userName;
    }

    public Map queryUserByempNo(String empno){
        JustechUser justechUser=userService.findByEmpId(empno);
        if (justechUser==null)
            return null;
        return JSONObject.parseObject(JSONObject.toJSONString(justechUser),Map.class);
    }

    /**
     * 查询用户信息
     * @param condition
     * @return
     */
    @Deprecated
    public Map  queryUserInRedis(String condition){
        String pattern = '*' + condition + '*';
        List<String> list = new ArrayList<>();

        Set<String> keySet = redisTemplate.keys(pattern);
        for (String key : keySet) {
            if (key.startsWith("user:")) {
                list.add(key);
            }
        }
        if (list.size() == 0) {
            return null;
        }

        Map user = redisTemplate.opsForHash().entries(list.get(0));
        if (user == null || user.isEmpty()) {
            return null;
        }
       return user;
    }

}
