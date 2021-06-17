package com.justech.mobile.mobileserver;

import com.alibaba.fastjson.JSONObject;
import com.justech.mobile.mobileserver.To.AccountTo;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.repository.UserEvaluationRepository;
import com.justech.mobile.mobileserver.service.UserService;
import com.justech.mobile.mobileserver.util.AesUtil;
import com.justech.mobile.mobileserver.util.HttpUtil;
import com.justech.mobile.mobileserver.util.RSAUtil;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MobileServerApplicationTests {

//    @Autowired
//    private AesUtil aesUtil;
//
//    @Test
//    public void contextLoads() throws Exception {
//
//        System.out.println(aesUtil.encrypt("000006353"));
//        System.out.println(aesUtil.decrypt("DXUwKxJ4fjh0MYQa5A8m9A=="));
//
//    }

//}
