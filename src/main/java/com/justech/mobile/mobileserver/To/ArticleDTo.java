package com.justech.mobile.mobileserver.To;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.io.File;
import java.util.Date;

/**
 * @ClassName ArticleDTo
 * @Author: xiaofeng.yang
 * @Date: Create in 8:57 2019/7/25
 * @Description : 文章添加实体
 * @Version: 1.0
 */
public class ArticleDTo {

    private String name;

    private int categoryId;

    private String introduction;

    private Integer openMode;

    private String url;

    private  Integer sort;

    private  Integer status;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private MultipartFile icon;

    private String content;

    private Integer contentId;
    private String iconUrl;
    public ArticleDTo() {

    }

    public ArticleDTo(String name, int categoryId, String introduction, Integer openMode, String url, Integer sort, Integer status, Date createTime
            , Date updateTime, String creater, MultipartFile icon, String content) {
        this.name = name;
        this.categoryId = categoryId;
        this.introduction = introduction;
        this.openMode = openMode;
        this.url = url;
        this.sort = sort;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.creater = creater;
        this.icon = icon;
        this.content = content;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getOpenMode() {
        return openMode;
    }

    public void setOpenMode(Integer openMode) {
        this.openMode = openMode;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public MultipartFile getIcon() {
        return icon;
    }

    public void setIcon(MultipartFile icon) {
        this.icon = icon;
    }
}
