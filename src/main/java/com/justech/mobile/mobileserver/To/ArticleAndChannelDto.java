package com.justech.mobile.mobileserver.To;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName Article
 * @Author: xiaofeng.yang
 * @Date: Create in 10:39 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
@Component
public class ArticleAndChannelDto {


    private Integer id;

    private String name;
    
    private int categoryId;

    private String introduction;

    private Integer openMode;

    private String url;

    private Integer sort;

    private Integer status;

    private String iconUri;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private String channelName;

    private String categoryName;

    public ArticleAndChannelDto() {

    }

    public ArticleAndChannelDto(ArticleDTo articleDTo) {
        this.categoryId = articleDTo.getCategoryId();
        this.creater = articleDTo.getCreater();
        this.introduction = articleDTo.getIntroduction();
        this.name = articleDTo.getName();
        this.openMode = articleDTo.getOpenMode();
        this.sort = articleDTo.getSort();
        this.status = articleDTo.getStatus();
    }

    public ArticleAndChannelDto(String name, int categoryId, String introduction, Integer openMode, String url, Integer sort, Integer status,
                                String iconUri, Date createTime, Date updateTime, String creater) {
        this.name = name;
        this.categoryId = categoryId;
        this.introduction = introduction;
        this.openMode = openMode;
        this.url = url;
        this.sort = sort;
        this.status = status;
        this.iconUri = iconUri;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.creater = creater;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
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
}

