package com.justech.mobile.mobileserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Application
 * @Author: xiaofeng.yang
 * @Date: Create in 10:41 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "block_id")
    private Integer blockId;
    @Column(name = "icon_uri")
    private String iconUrl;
    @Column(name = "introduction")
    private String introduction;
    @Column(name = "mode")
    private Integer mode;
    @Column(name = "uri")
    private String url;
    @Column(name = "sort")
    private Integer sort;
    @Column(name = "status")
    private Integer status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "creater")
    private String creater;

    @Column(name = "need_title")
    private String needTitle;
    @Column(name = "need_back_btn")
    private String needBackBtn;
    @Column(name = "sso_token")
    private String ssoToken;
    @Column(name = "is_beta")
    private String isBeta;

    public Application(){

    }

    public Application(String name, Integer blockId, String iconUrl, String introduction, Integer mode, String url, Integer sort, Integer status, Date createTime, String creater) {
        this.name = name;
        this.blockId = blockId;
        this.iconUrl = iconUrl;
        this.introduction = introduction;
        this.mode = mode;
        this.url = url;
        this.sort = sort;
        this.status = status;
        this.createTime = createTime;
        this.creater = creater;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getNeedTitle() {
        return needTitle;
    }

    public void setNeedTitle(String needTitle) {
        this.needTitle = needTitle;
    }

    public String getNeedBackBtn() {
        return needBackBtn;
    }

    public void setNeedBackBtn(String needBackBtn) {
        this.needBackBtn = needBackBtn;
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }

    public String getIsBeta() {
        return isBeta;
    }

    public void setIsBeta(String isBeta) {
        this.isBeta = isBeta;
    }
}


