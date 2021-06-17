package com.justech.mobile.mobileserver.entity;

import com.justech.mobile.mobileserver.To.ArticleDTo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName Article
 * @Author: xiaofeng.yang
 * @Date: Create in 10:39 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "category_id")
    private int categoryId;
    @Column(name = "introduction")
    private String introduction;
    @Column(name = "open_mode")
    private Integer openMode;
    @Column(name = "url")
    private String url;
    @Column(name = "sort")
    private  Integer sort;
    @Column(name = "status")
    private  Integer status;
    @Column(name = "icon_uri")
    private String icon;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "creater")
    private String creater;
    public Article(){

    }
    public Article(ArticleDTo articleDTo){
        this.categoryId=articleDTo.getCategoryId();
        this.creater=articleDTo.getCreater();
        this.introduction=articleDTo.getIntroduction();
        this.name=articleDTo.getName();
        this.openMode=articleDTo.getOpenMode();
        this.sort=articleDTo.getSort();
        this.status=articleDTo.getStatus();
    }

    public Article(String name, int categoryId, String introduction, Integer openMode, String url, Integer sort, Integer status, String icon, Date createTime, Date updateTime, String creater) {
        this.name = name;
        this.categoryId = categoryId;
        this.introduction = introduction;
        this.openMode = openMode;
        this.url = url;
        this.sort = sort;
        this.status = status;
        this.icon = icon;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return categoryId == article.categoryId &&
                Objects.equals(id, article.id) &&
                Objects.equals(name, article.name) &&
                Objects.equals(introduction, article.introduction) &&
                Objects.equals(openMode, article.openMode) &&
                Objects.equals(url, article.url) &&
                Objects.equals(sort, article.sort) &&
                Objects.equals(status, article.status) &&
                Objects.equals(icon, article.icon) &&
                Objects.equals(createTime, article.createTime) &&
                Objects.equals(updateTime, article.updateTime) &&
                Objects.equals(creater, article.creater);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryId, introduction, openMode, url, sort, status, icon, createTime, updateTime, creater);
    }
}

