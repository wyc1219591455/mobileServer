package com.justech.mobile.mobileserver.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealHdr {


    private String  docId;


    private String dmID;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dinerDate;


    private String dinerDeptID;


    private String creatorID;


    private String creatorName;


    private Date creatorDateTime;




}
