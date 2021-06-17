package com.justech.mobile.mobileserver.To;

import com.justech.mobile.mobileserver.entity.JustechUser;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 13:53 2019/10/23
 * @Description : TODO
 * @Version: 1.0
 */
public class JustechUserVo {
    private String id;
    private String humanId;
    private String empId;
    private String empName;
    private String job;
    private String sex;
    private String ext;
    private String userMobile;
    private String post;
    private  String email;
    private String icon;

    public JustechUserVo() {

    }
    public JustechUserVo(JustechUser user,String icon) {
        this.icon=icon;
        this.id=user.getId();
        this.humanId=user.getHumanId();
        this.empId=user.getEmpId();
        this.empName=user.getEmpName();
        this.job=user.getJob();
        this.sex=user.getSex();
        this.ext=user.getExt();
        this.userMobile=user.getUserMobile();
        this.post=user.getPost();
        this.email=user.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHumanId() {
        return humanId;
    }

    public void setHumanId(String humanId) {
        this.humanId = humanId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
