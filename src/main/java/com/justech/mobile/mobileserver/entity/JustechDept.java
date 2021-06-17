package com.justech.mobile.mobileserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "justech_dept")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class JustechDept {
    @Id
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

}
