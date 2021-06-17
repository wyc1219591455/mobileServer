package com.justech.mobile.mobileserver.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class JustechUser {
    @Id
    private String id;
    @JSONField(name = "FHumanID")
    private String humanId;
    @JSONField(name = "FOrgID")
    private String orgId;
    @JSONField(name = "FEmpID")
    private String empId;
    @JSONField(name = "FEmpIDNo")
    private String empIdNo;
    @JSONField(name = "FEmpName")
    private String empName;
    @JSONField(name = "FDeptID")
    private String deptId;
    @JSONField(name = "FJobId")
    private String jobId;
    @JSONField(name = "FJob")
    private String job;
    @JSONField(name = "FBirthday")
    private String birthday;
    @JSONField(name = "FSex")
    private String sex;
    @JSONField(name = "FInDate")
    private String inDate;
    @JSONField(name = "FSeniority")
    private String seniority;
    @JSONField(name = "FJobYears")
    private String jobYears;
    @JSONField(name = "FIDAdrress")
    private String idAdrress;
    @JSONField(name = "FManager")
    private String manager;
    @JSONField(name = "FUserStatus")
    private String userStatus;
    @JSONField(name = "FProbation")
    private String probation;
    @JSONField(name = "FMaritalStatus")
    private String maritalStatus;

    @JSONField(name = "FExt")
    private String ext;
    @JSONField(name = "FUserMobile")
    private String userMobile;
    @JSONField(name = "FOverTimeType")
    private String overTimeType;
    @JSONField(name = "FNationality")
    private String nationality;
    @JSONField(name = "FPost")
    private String post;
    @JSONField(name = "FPostId")
    private String postId;
    @JSONField(name = "FIsSwipe")
    private String isSwipe;
    @JSONField(name = "FNativePlace")
    private String nativePlace;
    @JSONField(name = "FEmergencyName")
    private String emergencyName;
    @JSONField(name = "FEmergencyMobile")
    private String emergencyMobile;
    @JSONField(name = "FPermanent")
    private String permanent;
    @JSONField(name = "FEducation")
    private String education;
    @JSONField(name = "FMajor")
    private String major;
    @JSONField(name = "FGraduateSchool")
    private String graduateSchool;
    @JSONField(name = "FIDCard")
    private String idCard;
    @JSONField(name = "FRecruitmentChannels")
    private String recruitmentChannels;
    @JSONField(name = "FUserType")
    private String userType;
    @JSONField(name = "FADACCOUNT")
    private String adAccount;
    @JSONField(name = "FCardSn")
    private String cardSn;
    @JSONField(name = "FCreateDateTime")
    private String fCreateDateTime;
    @JSONField(name = "FModifyDateTime")
    private String fModifyDateTime;
    @JSONField(name = "FEmail")
    private  String email;
    private Long version;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
