package com.justech.mobile.mobileserver.To;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName OrgEntity
 * @Author: xiaofeng.yang
 * @Date: Create in 14:42 2019/7/17
 * @Description : TODO
 * @Version: 1.0
 */
public class OrgEntity {
    private String orgID;
    private String name;
    private String orgNO;
    private String orgFullName;
    private String unUse;
    private String parentID;

    public OrgEntity() {

    }


    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgNO() {
        return orgNO;
    }

    public void setOrgNO(String orgNO) {
        this.orgNO = orgNO;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public String getUnUse() {
        return unUse;
    }

    public void setUnUse(String unUse) {
        this.unUse = unUse;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }
}
//  "FOrgID": "7dc16e86-172c-4f96-bd16-9a0fd82d6262",
//          "FOrgNO": "JST000000000000000",
//          "FOrgName": "杰士德集团",
//          "FOrgFullName": "杰士德集团",
//          "FUnUse": false,
//          "FParentID": "0"