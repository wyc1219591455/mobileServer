package com.justech.mobile.mobileserver.service;

import com.justech.mobile.mobileserver.To.AccountTo;
import com.justech.mobile.mobileserver.To.OAUserTo;
import com.justech.mobile.mobileserver.To.UserOfErpEntity;
import com.justech.mobile.mobileserver.entity.JustechUser;
import com.justech.mobile.mobileserver.repository.JustechUserRepository;
import com.justech.mobile.mobileserver.repository.UserRepostitory;
import com.justech.mobile.mobileserver.util.CumstonException;
import com.justech.mobile.mobileserver.util.HttpUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserService
 * @Author: xiaofeng.yang
 * @Date: Create in 9:24 2019/7/10
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class UserService {



    @Autowired
    private UserRepostitory userRepostitory;
    @Autowired
    private JustechUserRepository justechUserRepository;
    @Value("${oa.server}")
    private String oaServerUrl;
    @Value("${oa.syscorpid}")
    private String syscorpid;
    @Value("${oa.outsysid}")
    private String outsysid;
    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public AccountTo identification(String userName, String password) throws IOException, CumstonException {

        return userRepostitory.identification(userName,password);
    }

    /**
     * @Name getUserInfo
     * @Author xiaofeng.yang
     * @Description 获取员工信息
     * @Date 9:24 2019/7/11
     * @Param [id]
     * @return java.util.Map
     **/
    public UserOfErpEntity getUserInfo(String id){
        return userRepostitory.readOnlyUserInfo(id);
    }



    /**
     * 获取用户信息
     * @param key
     * @return
     */
    public UserOfErpEntity getUserDetails(String key){
        return userRepostitory.getUserDetails(key);
    }

    public boolean asyncUser(OAUserTo oaUserTo) throws IOException {
        StringBuffer key = new StringBuffer();
        key.append("ebridge").append("|").append(oaUserTo.getUserid()).append("|").append(oaUserTo.getUsername()).append("|").append(oaUserTo.getMobile());
        String value = redisTemplate.opsForValue().get(key.toString());
        if(value!=null){
            return true;
        }
        HttpPost httpPost=new HttpPost(oaServerUrl+"/account");
        Map map=new HashMap();
        String body="{\n" +
                "\"userid\":\""+oaUserTo.getUserid()+"\",\n" +
                "\"username\":\""+oaUserTo.getUsername()+"\",\n" +
                "\"mobile\":\""+oaUserTo.getMobile()+"\",\n" +
                "\"syscorpid\":\""+syscorpid+"\",\n" +
                "\"outsysid\":\""+outsysid+"\"\n" +
                "}";
        body=new String(body.getBytes("utf8"),"utf8");
        StringEntity entity=new StringEntity(body,"UTF-8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        entity.setContentEncoding("UTF-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response=httpUtil.doPost(httpPost);
        if (response.getStatusLine().getStatusCode()==200)
        {
            httpPost.abort();
            redisTemplate.opsForValue().set(key.toString(),oaUserTo.getUserid()+": have binded ebridge");//永久放入缓存
            return true;
        }
        httpPost.abort();
        return false;
    }

    public JustechUser findbyphonenum(String num){
        return justechUserRepository.findTopByUserMobileAndUserStatus(num,"1");
    }
    public JustechUser findByEmpId(String empId){
        return justechUserRepository.findTopByEmpId(empId);
    }

}
