package com.justech.mobile.mobileserver.entity;



import com.justech.mobile.mobileserver.util.ListStringConvert;
import com.justech.mobile.mobileserver.util.UserevaluationIdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_evaluation")
@IdClass(value = UserevaluationIdClass.class)
@Getter
@Setter
@NoArgsConstructor
public class UserEvaluation   implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String text;
  @Convert(converter = ListStringConvert.class)
  private List<String> uId;
  @Id
  private Integer weight;
  private Long version;

}
