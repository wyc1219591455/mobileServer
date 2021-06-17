package com.justech.mobile.mobileserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * clay
 */
@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    /**
     * 工号
     */
    @Column(name = "empno")
    private String empno;

    /**
     * 用户名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 长号
     */
    @Column(name = "longnum")
    private String longnum;

    /**
     * 短号
     */
    @Column(name = "shortnum")
    private String shortnum;

    /**
     * 性别 "0 ：男, 1：女"
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 公司
     */
    @Column(name = "company")
    private String company;

    /**
     * 部门
     */
    @Column(name = "dept")
    private String dept;

    /**
     * 岗位
     */
    @Column(name = "job")
    private String job;

    /**
     * 上级主管
     */
    @Column(name = "manager")
    private String manager;

    /**
     * 部门助理
     */
    @Column(name = "assitant")
    private Date assitant;

    /**
     * 入职时间
     */
    @Column(name = "employtime")
    private String employtime;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 紧急联系人
     */
    @Column(name = "emergencycontact")
    private String emergencycontact;

    /**
     * 紧急联系人联系方式
     */
    @Column(name = "emergencynum")
    private String emergencynum;

}

