package com.justech.mobile.mobileserver.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * clay
 */
@Entity
@Table(name = "thirdparty")
@Data
public class Thirdparty {
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
     * 用户昵称
     */
    @Column(name = "nick")
    private String nick;

    /**
     * openid 应用
     */
    @Column(name = "openid")
    private String openid;

    /**
     * 授权类型
     */
    @Column(name = "type")
    private String type;


}

