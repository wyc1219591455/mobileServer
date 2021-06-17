package com.justech.mobile.mobileserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POST_FUNCTION_USER")
@Getter
@Setter
@NoArgsConstructor
public class PostFunctionUser {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "fun_name")
  private String funName;
  @Column(name = "emp_no")
  private String empNo;
  @Column(name = "user_name")
  private String userName;
  @Column(name = "human_id")
  private String humanId;
  @Column(name = "sort")
  private int sort;
  @Column(name = "t_id")
  private int tId;

}
