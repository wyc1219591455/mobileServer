package com.justech.mobile.mobileserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_icon")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserIcon {
  @Id
  private String userId;
  //文件服务器dfs
  private String mdFive;
  private String path;
  //图片md5
  private String imgMd;




}
