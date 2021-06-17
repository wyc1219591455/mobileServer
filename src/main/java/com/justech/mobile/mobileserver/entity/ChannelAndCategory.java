package com.justech.mobile.mobileserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName ChannelAndCategory
 * @Author: xiaofeng.yang
 * @Date: Create in 9:08 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
@Entity
@Table(name = "channel_and_category")
public class ChannelAndCategory {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    //名称
    @Column(name = "name", nullable = false)
    private String name;
    //层级
    @Column(name = "level", nullable = false)
    private Integer level;
    @Column(name = "p_id")
    //上级id
    private Integer pid;
    //状态：0禁用 1启用
    @Column(name = "status")
    private int status;
    //图标
    @Column(name = "icon")
    private String icon;
    //排序
    @Column(name = "sort")
    private Integer sort;
    //评论开关,0关闭，1打开
    @Column(name = "is_comment")
    private Integer comment;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "creater")
    private String creater;

    public ChannelAndCategory() {

    }

    public ChannelAndCategory(String name, Integer level, Integer pid, int status, String icon, Integer sort, Integer comment, Date createTime,
                              Date updateTime, String creater) {
        this.name = name;
        this.level = level;
        this.pid = pid;
        this.status = status;
        this.icon = icon;
        this.sort = sort;
        this.comment = comment;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
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
