package com.justech.mobile.mobileserver.To;

import com.justech.mobile.mobileserver.entity.JustechDept;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 10:48 2019/10/23
 * @Description : TODO
 * @Version: 1.0
 */
public class JustechDeptVo {
    private String id;

    private String name;

    private String orgId;

    private String parentId;

    private String unUse;

    private String type;

    private String assistant;

    private String fgzxfzrCode;

    private String bmyjfzrCode;

    private String bmejfzrCode;

    private String bmsjfzrCode;

    private String manager;

    private Long num;

    public JustechDeptVo() {

    }

    public JustechDeptVo(JustechDept dept, Long num) {
        this(dept.getId(), dept.getName(), dept.getOrgId(), dept.getParentId(), dept.getUnUse(), dept.getType(), dept.getAssistant(),
                dept.getFgzxfzrCode(), dept.getBmyjfzrCode(), dept.getBmejfzrCode(), dept.getBmsjfzrCode(), dept.getManager(), num);
    }


    public JustechDeptVo(String id, String name, String orgId, String parentId, String unUse, String type, String assistant, String fgzxfzrCode,
                         String bmyjfzrCode, String bmejfzrCode, String bmsjfzrCode, String manager, Long num) {
        this.id = id;
        this.name = name;
        this.orgId = orgId;
        this.parentId = parentId;
        this.unUse = unUse;
        this.type = type;
        this.assistant = assistant;
        this.fgzxfzrCode = fgzxfzrCode;
        this.bmyjfzrCode = bmyjfzrCode;
        this.bmejfzrCode = bmejfzrCode;
        this.bmsjfzrCode = bmsjfzrCode;
        this.manager = manager;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUnUse() {
        return unUse;
    }

    public void setUnUse(String unUse) {
        this.unUse = unUse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getFgzxfzrCode() {
        return fgzxfzrCode;
    }

    public void setFgzxfzrCode(String fgzxfzrCode) {
        this.fgzxfzrCode = fgzxfzrCode;
    }

    public String getBmyjfzrCode() {
        return bmyjfzrCode;
    }

    public void setBmyjfzrCode(String bmyjfzrCode) {
        this.bmyjfzrCode = bmyjfzrCode;
    }

    public String getBmejfzrCode() {
        return bmejfzrCode;
    }

    public void setBmejfzrCode(String bmejfzrCode) {
        this.bmejfzrCode = bmejfzrCode;
    }

    public String getBmsjfzrCode() {
        return bmsjfzrCode;
    }

    public void setBmsjfzrCode(String bmsjfzrCode) {
        this.bmsjfzrCode = bmsjfzrCode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
