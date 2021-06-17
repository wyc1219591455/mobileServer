package com.justech.mobile.mobileserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Block
 * @Author: xiaofeng.yang
 * @Date: Create in 11:26 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
@Entity
@Table(name = "block")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "width")
    private Integer width;
    @Column(name = "height")
    private Integer height;
    @Column(name = "sort")
    private Integer sort;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "creater")
    private String creater;
    @Column(name = "status")
    private Integer status;

    public Block() {

    }

    public Block(Integer id, String name, Integer width, Integer height, Integer sort, Date createTime, String creater) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.sort = sort;
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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
}
