package com.justech.mobile.mobileserver.To;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName JobEntity
 * @Author: xiaofeng.yang
 * @Date: Create in 14:58 2019/7/17
 * @Description : TODO
 * @Version: 1.0
 */
public class JobEntity {
    private String id;
    private String name;

    public JobEntity() {

    }

    public JobEntity(String id, String name) {
        this.id = id;
        this.name = name;
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
}
