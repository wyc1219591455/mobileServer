package com.justech.mobile.mobileserver.To;

import java.util.List;

/**
 * @ClassName OAUserTo
 * @Author: xiaofeng.yang
 * @Date: Create in 13:59 2019/7/11
 * @Description : 返回泛微云用户数据结构
 * @Version: 1.0
 */
public class OAUserTo {

    //错误码，必须
    private Integer errcode;
    //错误信息，必须
    private String errmsg;
    //用户标识，必须
    private String userid;
    //用户姓名，可选
    private String username;
    //手机号，可选
    private String mobile;
    //邮箱，可选
    private String email;
    //部门编号，可选
    private List<Integer> department;
    //岗位，可选
    private String position;
    //头像，可选
    private String avatar;
    //状态，可选
    private Integer status;
    public OAUserTo(){

    }

    public OAUserTo(Integer errcode, String errmsg, String userid, String username, String mobile) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.userid = userid;
        this.username = username;
        this.mobile = mobile;
    }

    public OAUserTo(Integer errcode, String errmsg, String userid) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.userid = userid;
    }

    public OAUserTo(Integer errcode, String errmsg, String userid, String username, String mobile, String email, List<Integer> department, String position, String avatar, Integer status) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.userid = userid;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.department = department;
        this.position = position;
        this.avatar = avatar;
        this.status = status;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
