package com.justech.mobile.mobileserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justech.mobile.mobileserver.To.AccountTo;
import com.justech.mobile.mobileserver.To.OAUserTo;
import com.justech.mobile.mobileserver.To.UserOfErpEntity;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.entity.JustechUser;
import com.justech.mobile.mobileserver.entity.UserEvaluation;
import com.justech.mobile.mobileserver.model.SmsReqModel;
import com.justech.mobile.mobileserver.repository.UserEvaluationRepository;
import com.justech.mobile.mobileserver.service.UserService;
import com.justech.mobile.mobileserver.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserController
 * @Author: xiaofeng.yang
 * @Date: Create in 10:02 2019/7/10
 * @Description : TODO
 * @Version: 1.0
 */
@RestController
public class UserController extends CommonController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //    @Autowired
//    private AccountService accountService;
    @Autowired
    private UserEvaluationRepository userEvaluationRepository;

//    @Autowired
//    private ThirtpartyService thirtpartyService;

    @Autowired
    private AesUtil aesUtil;
    @Autowired
    HttpUtil httpUtil;
    @Value("${erp.server.user.caterRecord.url}")
    private String caterRecordUrl;
    @Value("${erp.server.user.caterException.url}")
    private String caterExceptionUrl;

    @Value("${rsa.publicKey}")
    private String rsaPublicKey;
    @Value("${rsa.privateKey}")
    private String rsaPrivateKey;
    @Value("${expirTime}")
    private int expireTime;
//    @Value("${dingtalk.appId}")
//    private String appid;
//    @Value("${dingtalk.appSecret}")
//    private String appSecret;


//    @Autowired
//    private DingTalkService dingTalkService;

    /**
     * 绑定云桥
     *
     * @param appid
     * @param token
     * @param code
     * @return
     */
    @RequestMapping(value = "/oauser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OAUserTo ebridge(@RequestParam(value = "appid", required = false) String appid, @RequestParam(value = "access_token", required = false) String token, @RequestParam(value = "code", required = true) String code) {
        try {
            String empNo = aesUtil.decrypt(code);
            UserOfErpEntity user = userService.getUserInfo(empNo);
            if (!"1".equals(user.getUserStatus())) {
                return new OAUserTo(ResultEnum.BAD.getCode(), "user status is leave office", null);
            }
            if (user == null) return new OAUserTo(ResultEnum.BAD.getCode(), ResultEnum.BAD.getMsg(), null);
            OAUserTo oaUserTo = new OAUserTo(0, "", empNo, user.getUserName(), user.getMobile());
            if (userService.asyncUser(oaUserTo))
                return oaUserTo;
            else
                return new OAUserTo(ResultEnum.BAD.getCode(), "bind user error", null);
        } catch (GeneralSecurityException e) {
            return new OAUserTo(ResultEnum.BAD.getCode(), ResultEnum.BAD.getMsg(), null);
        } catch (IOException e) {
            e.printStackTrace();
            return new OAUserTo(ResultEnum.BAD.getCode(), "bind user error", null);
        }

    }

    /**
     * 用户登录验证
     *
     * @param accountTo
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response identyUser(@RequestBody AccountTo accountTo) {
        try {

            System.out.println("Hello");
            // 用户名 + 密码 验证
            AccountTo identyTo = userService.identification(accountTo.getAccount(), accountTo.getPassword());
            identyTo.setAccount(accountTo.getAccount());
            if (identyTo.getAdPwdNeedReset())
                return new Response().of(ResultEnum.NEW_USER_RESETPASSWORD, null);
            if (identyTo.getAdPwdIsExpired())
                return new Response().of(ResultEnum.PASSWORD_EXPRIED, null);
            if (identyTo.getEmpNo() == null || identyTo.getEmpNo().isEmpty())
                return new Response().of(ResultEnum.INVALIDPARAM, null);
            StringBuffer tokenbuffer = new StringBuffer(rsaPrivateKey).append(".").append(aesUtil.encrypt(identyTo.getEmpNo())).append(".");
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("expirTime", new Date().getTime() / 1000 + expireTime);
            // jsonObject.put("empNo", identyTo.getEmpNo());
            jsonObject.put("empNo", "000006353");
            String rsaToken = RSAUtil.rsaEncrypt(jsonObject.toString(), rsaPublicKey);
            tokenbuffer.append(rsaToken);
            //---------------将token 放入缓存中 start----------------
            StringBuffer key = new StringBuffer();
            key.append("token").append(":").append(identyTo.getEmpNo()).toString();
            Map<String, String> map = new HashMap<>();
            map.put("empNo", identyTo.getEmpNo());
            map.put("authorization", tokenbuffer.toString());

            //System.err.println(tokenbuffer.toString());
            int h = rsaToken.hashCode();
            redisTemplate.opsForHash().putAll(key.toString() + "_" + (h & h >>> 16), map);

            redisTemplate.expire(key.toString() + "_" + (h & h >>> 16), expireTime, TimeUnit.SECONDS);
            //---------------将token 放入缓存中 end----------------
            return new Response().of(ResultEnum.SUCCESS, new AccountTo(identyTo.getAccount(), tokenbuffer.toString(), null));
        } catch (GeneralSecurityException e) {
            logger.error("identyUser aes error：", e);
            return new Response().of(ResultEnum.BAD, null);
        } catch (CumstonException e) {
            logger.error("identyUser aes error：", e);
            return new Response().of(ResultEnum.BAD, e.getMessage(), null);
        } catch (IOException e) {
            logger.error("identyUser aes error：", e);
            return new Response().of(ResultEnum.BAD, null);
        } catch (Exception e) {
            logger.error("identyUser aes error：", e);
            return new Response().of(ResultEnum.INTERNALFAIL, null);
        }
    }


    /**
     * 发送手机验证码 （审核固定账号 18021291885 ;密码 2888）
     *
     * @param phone
     * @return
     */
    @GetMapping(value = "/sendSms", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sendSMS(@RequestParam("phone") String phone) {
        try {
            if (!phone.equals("18021291885")) {
                smsService.sendSMS(phone);
            }
        } catch (CumstonException e) {
            return getResponse(ResultEnum.USER_NOT_FIND.getCode(), e.getMessage());
        }
        return new Response().of(ResultEnum.SUCCESS, null);
    }

    /**
     * 根据手机号登录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/loginBySmsCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response identyUserByCode(SmsReqModel smsReqModel) {
        try {
            Response response = getResponse(ResultEnum.SUCCESS);
            if (!beanValidator(response, smsReqModel)) return response;
            String empNo;
            if (smsReqModel.getPhone().equals("18021291885") && "2888".equals(smsReqModel.getCode())) {
                empNo = "000004798";
            } else {
                if (!smsService.validateSmsCode(smsReqModel.getPhone(), smsReqModel.getCode())) {
                    return new Response().of(ResultEnum.SMS_CODE_ERROR, null);
                }
                JustechUser justechUser = userService.findbyphonenum(smsReqModel.getPhone());
                if (justechUser == null) {
                    logger.error(ResultEnum.USER_NOT_FIND.getMsg() + "|" + "手机号：" + smsReqModel.getPhone());
                    return new Response().of(ResultEnum.USER_NOT_FIND, null);
                }
                Map user = JSONObject.parseObject(JSONObject.toJSONString(justechUser), Map.class);
                if (!user.containsKey("FEmpID") || user.get("FEmpID") == null || user.get("FEmpID").toString().trim().isEmpty())
                    return new Response().of(ResultEnum.INVALIDPARAM, null);
                empNo = user.get("FEmpID").toString();
            }

            StringBuffer tokenbuffer = new StringBuffer(rsaPrivateKey).append(".").append(aesUtil.encrypt(empNo)).append(".");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("expirTime", new Date().getTime() / 1000 + expireTime);
            jsonObject.put("empNo", empNo);
            jsonObject.put("phone", smsReqModel.getPhone());
            String rsaToken = RSAUtil.rsaEncrypt(jsonObject.toString(), rsaPublicKey);
            tokenbuffer.append(rsaToken);
            if (!smsReqModel.equals("18021291885")) {//永久测试账号
                String expireKey = new StringBuffer("message|expire").append(":").append(smsReqModel.getPhone()).toString();
                redisTemplate.delete(expireKey);
            }
            //---------------将token 放入缓存中 start----------------
            StringBuffer key = new StringBuffer();
            key.append("token").append(":").append(empNo).toString();
            Map<String, String> map = new HashMap<>();
            map.put("empNo", empNo);
            map.put("authorization", tokenbuffer.toString());
            int h = rsaToken.hashCode();
            redisTemplate.opsForHash().putAll(key.toString() + "_" + (h & h >>> 16), map);

            redisTemplate.expire(key.toString() + "_" + (h & h >>> 16), expireTime, TimeUnit.SECONDS);
            //---------------将token 放入缓存中 end----------------

            return new Response().of(ResultEnum.SUCCESS, new AccountTo(smsReqModel.getPhone(), tokenbuffer.toString(), null));
        } catch (Exception e) {
            logger.error("identyUser aes error：", e);
            return new Response().of(ResultEnum.BAD, null);
        }
    }

    /**
     * 获取用户信息
     *
     * @param number
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getUserInfo(@RequestParam(name = "number", required = true) String number) {
        try {
            UserOfErpEntity user = userService.getUserInfo(aesUtil.decrypt(number));
            if (user == null) return new Response().of(ResultEnum.INVALIDPARAM, "param: " + number);
            return new Response().of(ResultEnum.SUCCESS, user);
        } catch (GeneralSecurityException e) {
            logger.error("getUserInfo aes error：", e);
            return new Response().of(ResultEnum.BAD, null);
        }
    }


    /**
     * 获取联系人
     *
     * @param condition
     */

//    @RequestMapping(value = "/contracts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Deprecated
    public Response getContracts(String condition) {
        String suffix = '*' + condition.toLowerCase() + '*';
        Set<String> keys = redisTemplate.keys(suffix);
        List<String> list = new ArrayList<>();
        for (String key : keys) {
            if (key.startsWith("user:pingyin:")) {
                list.add(key);
            }
        }
        List<String> sublist = list;
//        if (list.size() > 10) {
//            sublist = list.subList(0, 9);
//        } else {
//            sublist = list;
//        }
        Set<UserOfErpEntity> result = new HashSet<>();
        int conut = 0;
        for (int i = 0; i < sublist.size(); i++) {
            UserOfErpEntity userDetails = userService.getUserDetails(sublist.get(i));
            if ("1".equals(userDetails.getUserStatus())) {
                result.add(userDetails);
            }
            if (result.size() > 9) {
                break;
            }
        }
        return new Response().of(ResultEnum.SUCCESS, result);
    }

    /**
     * 通讯录
     *
     * @param condition
     * @return
     */
    @RequestMapping(value = "/contracts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getContractsnew(String condition) {
        if (StringUtils.isBlank(condition))
            return null;
        Set<String> ids = new LinkedHashSet<>();
        List<UserEvaluation> isuserEvaluations = userEvaluationRepository.findAllByTextOrderByWeightDesc(condition.toLowerCase());
        for (UserEvaluation userEvaluation : isuserEvaluations) {
            for (String id : userEvaluation.getUId()) {
                ids.add(id);
            }
        }
        if (ids.size() < 10) {
            List<UserEvaluation> isuserEvaluationslike = userEvaluationRepository.findAllByTextContainingOrderByWeightDesc(condition.toLowerCase());
            for (UserEvaluation userEvaluation : isuserEvaluationslike) {
                for (String id : userEvaluation.getUId()) {
                    ids.add(id);
                }
            }
        }
        List<UserOfErpEntity> result = new ArrayList<>();
        for (String key : ids) {
            UserOfErpEntity userDetails = userService.getUserDetails(key);
            if ("1".equals(userDetails.getUserStatus())) {
                result.add(userDetails);
            }
            if (result.size() > 9) {
                break;
            }
        }
        return result;
    }

    /**
     * 获取上级信息
     *
     * @param managerWorkId
     */
    @RequestMapping(value = "/managerInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getManagerInfo(String managerWorkId) {
        UserOfErpEntity user = userService.getUserInfo(managerWorkId);
        if (user == null) return new Response().of(ResultEnum.INVALIDPARAM, "param: " + managerWorkId);
        return new Response().of(ResultEnum.SUCCESS, user);
    }

    /**
     * 用户退出接口
     *
     * @param empNo
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response logout(String empNo) {
        Response response = getResponse(ResultEnum.SUCCESS);
        StringBuffer key = new StringBuffer();
        key.append("token").append(":").append(empNo).toString();
        Map value = redisTemplate.opsForHash().entries(key.toString());
        if (value.size() > 0) {
            redisTemplate.delete(key.toString());
        }
        return response;
    }

//    /**
//     * 用户授权
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/grant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Response grant(GrantReqModel model) {
//        Response res = getResponse(ResultEnum.SUCCESS);
//        if (!beanValidator(res, model)) return res;
//        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
//        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
//        req.setTmpAuthCode(model.getAuthCode());
//        try {
//            OapiSnsGetuserinfoBycodeResponse response = client.execute(req, appid, appSecret);
//            OapiSnsGetuserinfoBycodeResponse.UserInfo userInfo = null;
//            if ("ok".equals(response.getErrmsg())) {
//                userInfo = response.getUserInfo();
//                //关联账户信息
//                Account account = accountService.getAccountByEmpno(model.getEmpNo());// 确认在用户表中
//                if (account != null) {
//                    Thirdparty thirdparty = thirtpartyService.getThirdpartiesByEmpnoAndOpenid(account.getEmpno(), userInfo.getOpenid());//是否已经绑定
//                    if (thirdparty != null) {
//                        return res;
//                    }
//                    String empno = account.getEmpno();
//                    thirdparty = new Thirdparty();
//                    thirdparty.setEmpno(empno);
//                    thirdparty.setNick(userInfo.getNick());
//                    thirdparty.setOpenid(userInfo.getOpenid());
//                    thirdparty.setType(model.getType());
//                    thirtpartyService.save(thirdparty);
//                } else {
//                    res = getResponse(ResultEnum.GRANT_FAILED);
//                }
//            } else {
//                res = getResponse(ResultEnum.GRANT_FAILED);
//            }
//        } catch (ApiException e) {
//            logger.error(e.getErrMsg());
//        }
//        return res;
//    }
//
//
//    /**
//     * 授权解绑
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/unbind", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Response grant(UnbindReqModel model) {
//        Response res = getResponse(ResultEnum.SUCCESS);
//        if (!beanValidator(res, model)) return res;
//        Account account = accountService.getAccountByEmpno(model.getEmpNo());// 确认在用户表中
//        if (account != null) {
//            Thirdparty thirdparty = thirtpartyService.findByEmpnoAndType(account.getEmpno(), model.getType());//是否已经绑定
//            if (thirdparty == null) {
//                return getResponse(ResultEnum.UNBIND_FAILED);
//            }
//            thirtpartyService.delete(thirdparty);
//        } else {
//            return getResponse(ResultEnum.ACCOUNT_NOT_FIND);
//        }
//        return res;
//    }
//
//
//    /**
//     * 授权解绑
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/checkBindStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Response checkBindStatus(UnbindReqModel model) {
//        Response res = getResponse(ResultEnum.SUCCESS);
//        if (!beanValidator(res, model)) return res;
//        Account account = accountService.getAccountByEmpno(model.getEmpNo());// 确认在用户表中
//        if (account != null) {
//            Thirdparty thirdparty = thirtpartyService.findByEmpnoAndType(account.getEmpno(), model.getType());//是否已经绑定
//            if (thirdparty == null) {
//                res.setData(false);
//            } else {
//                res.setData(true);
//            }
//            return res;
//        } else {
//            res.setData(false);
//            return res;
//        }
//
//    }


}
