package com.justech.mobile.mobileserver.entity;



import com.justech.mobile.mobileserver.util.ListStringConvert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table(name = "justech_org_tree")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class JustechOrgTree {
  @Id
  private String id;
  @Convert(converter = ListStringConvert.class)
  private List<String> childIds;
  @Convert(converter = ListStringConvert.class)
  private List<String> parentIds;
  private Long pNum;
}
