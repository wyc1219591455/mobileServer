package com.justech.mobile.mobileserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "POST_FUNCTION_TYPE")
@Getter
@Setter
@NoArgsConstructor
public class PostFunctionType {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "p_id")
  private Integer pId;
  @Column(name = "name")
  private String name;
  @Column(name = "sort")
  private int sort;
  @Column(name = "level")
  private int level;


}
