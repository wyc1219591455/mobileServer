package com.justech.mobile.mobileserver.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDtl implements Serializable {

    private String  docId;


    private String dinerID;

    private String dinerName;

    private String dinerDeptID;

    private String dinerDeptName;

    private String dinerType;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dinerDate;

    private String creatorName;



}
