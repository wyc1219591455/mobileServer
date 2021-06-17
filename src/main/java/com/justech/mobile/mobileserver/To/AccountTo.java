package com.justech.mobile.mobileserver.To;

/**
 * @ClassName AccountTo
 * @Author: xiaofeng.yang
 * @Date: Create in 10:38 2019/7/11
 * @Description : TODO
 * @Version: 1.0
 */
public class AccountTo {
    private String account;
    private String password;
    private String token;
//    @JSONField(name = "EmpNo")
    private String empNo;
    private Boolean  adPwdIsExpired;
    private Boolean  adPwdNeedReset;
    public AccountTo(){

    }

    public AccountTo(String account, String token, String empNo) {
        this.account = account;
        this.token = token;
        this.empNo = empNo;
    }

    public Boolean getAdPwdIsExpired() {
        return adPwdIsExpired;
    }

    public void setAdPwdIsExpired(Boolean adPwdIsExpired) {
        this.adPwdIsExpired = adPwdIsExpired;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAdPwdNeedReset() {
        return adPwdNeedReset;
    }

    public void setAdPwdNeedReset(Boolean adPwdNeedReset) {
        this.adPwdNeedReset = adPwdNeedReset;
    }
}
