package com.justech.mobile.mobileserver.To;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName DeptEntity
 * @Author: xiaofeng.yang
 * @Date: Create in 10:21 2019/7/17
 * @Description : TODO
 * @Version: 1.0
 */
public class DeptEntity {
//    @JSONField(name = "deptID")
    private String deptID;
//    @JSONField(name = "deptName")
    private String deptName;
//    @JSONField(name = "orgID")
    private String orgID;
//    @JSONField(name = "parentID")
    private String parentID;
//    @JSONField(name = "unUse")
    private Boolean unUse;
//    @JSONField(name = "deptType")
    private Integer deptType;
//    @JSONField(name = "assistant")
    private String assistant;

    public DeptEntity() {

    }

    public DeptEntity(String deptID, String deptName, String orgID, String parentID, Boolean unUse, Integer deptType, String assistant) {
        this.deptID = deptID;
        this.deptName = deptName;
        this.orgID = orgID;
        this.parentID = parentID;
        this.unUse = unUse;
        this.deptType = deptType;
        this.assistant = assistant;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public Boolean getUnUse() {
        return unUse;
    }

    public void setUnUse(Boolean unUse) {
        this.unUse = unUse;
    }

    public Integer getDeptType() {
        return deptType;
    }

    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }
}

