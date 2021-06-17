package com.justech.mobile.mobileserver.To;

import com.justech.mobile.mobileserver.entity.JustechOrg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 10:47 2019/10/23
 * @Description : TODO
 * @Version: 1.0
 */

public class JustechOrgVo {
    private String id;

    private String orgNo;

    private String orgName;

    private String orgFullName;

    private String unUse;

    private String ordSeq;

    private String parentId;

    private Long num;

    public JustechOrgVo() {

    }

    public JustechOrgVo(String id, String orgNo, String orgName, String orgFullName, String unUse, String ordSeq, String parentId, Long num) {
        this.id = id;
        this.orgNo = orgNo;
        this.orgName = orgName;
        this.orgFullName = orgFullName;
        this.unUse = unUse;
        this.ordSeq = ordSeq;
        this.parentId = parentId;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public String getOrdSeq() {
        return ordSeq;
    }

    public void setOrdSeq(String ordSeq) {
        this.ordSeq = ordSeq;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
