package com.justech.mobile.mobileserver.To;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName UserOfErpEntity
 * @Author: xiaofeng.yang
 * @Date: Create in 15:52 2019/7/17
 * @Description : TODO
 * @Version: 1.0
 */
public class UserOfErpEntity {


    /*
     * @Name
     * @Author xiaofeng.yang
     * @Description 以下是基本信息
     * @Date 16:02 2019/7/17
     * @Param
     * @return
     **/
    //头像url
    private String imageUrl;


    /**
     * 工号id
     */
    @JSONField(name = "FEmpID")
    private String workId;
    @JSONField(name = "FEmpName")
    private String userName;
    @JSONField(name = "FSex")
    private String sex;
    @JSONField(name = "FBirthday")
    private String birthday;
    @JSONField(name = "FUserMobile")
    private String mobile;
    //短号
    @JSONField(name = "FExt")
    private String ext;
    @JSONField(name = "FEmail")
    private String email;
    /*
     * @Name
     * @Author xiaofeng.yang
     * @Description 以下是工作信息
     * @Date 16:03 2019/7/17
     * @Param
     * @return
     **/
    @JSONField(name = "FOrgID")
    private String org;
    @JSONField(name ="FDeptID" )
    private String dept;
    @JSONField(name = "FPost")
    private String post;
    //部门助理
    private String assistant;
    //直属上级
    @JSONField(name = "FManager")
    private String manager;
    //直属上级工号id add by clay



    /**
     *
     */
    @JSONField(name = "FManagerWorkId")
    private String managerWorkId;
    //入职日期
    @JSONField(name = "FInDate")
    private String inDate;
    //转正日期
    private String correctionDate;
    //合同
    private String contract;
    @JSONField(name = "FJobId")
    private String job;


    /*
     * @Name
     * @Author xiaofeng.yang
     * @Description 以下是应急信息
     * @Date 16:03 2019/7/17
     * @Param
     * @return
     **/
    //居住地址
    @JSONField(name = "FDwellingAdrress")
    private String dwellingAdrress;
    //紧急联系人
    @JSONField(name = "FEmergencyName")
    private String emergencyName;
    //紧急联系人电话
    @JSONField(name = "FEmergencyMobile")
    private String emergencymobile;
    @JSONField(name = "FUserStatus")
    private String userStatus;

    public UserOfErpEntity() {
    }

    public UserOfErpEntity(String userName, String sex, String birthday, String mobile, String ext, String email, String org, String dept, String post, String assistant, String manager, String inDate, String correctionDate, String contract, String dwellingAdrress, String emergencyName, String emergencymobile) {
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
        this.mobile = mobile;
        this.ext = ext;
        this.email = email;
        this.org = org;
        this.dept = dept;
        this.post = post;
        this.assistant = assistant;
        this.manager = manager;
        this.inDate = inDate;
        this.correctionDate = correctionDate;
        this.contract = contract;
        this.dwellingAdrress = dwellingAdrress;
        this.emergencyName = emergencyName;
        this.emergencymobile = emergencymobile;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getCorrectionDate() {
        return correctionDate;
    }

    public void setCorrectionDate(String correctionDate) {
        this.correctionDate = correctionDate;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getDwellingAdrress() {
        return dwellingAdrress;
    }

    public void setDwellingAdrress(String dwellingAdrress) {
        this.dwellingAdrress = dwellingAdrress;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencymobile() {
        return emergencymobile;
    }

    public void setEmergencymobile(String emergencymobile) {
        this.emergencymobile = emergencymobile;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getManagerWorkId() {
        return managerWorkId;
    }

    public void setManagerWorkId(String managerWorkId) {
        this.managerWorkId = managerWorkId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public int hashCode() {
        return this.workId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserOfErpEntity){
            UserOfErpEntity userOfErpEntity= (UserOfErpEntity) obj;
            return this.workId.equals(userOfErpEntity.getWorkId());
        }
        return false;
    }
}
