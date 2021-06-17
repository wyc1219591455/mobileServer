package com.justech.mobile.mobileserver.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "justech_org")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class JustechOrg {
    @Id
    private String id;

    private String orgNo;

    private String orgName;

    private String orgFullName;

    private String unUse;

    private String ordSeq;

    private String parentId;


}
