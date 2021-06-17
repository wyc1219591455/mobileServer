package com.justech.mobile.mobileserver.model.bean;

import lombok.Data;


/**
 * @author clay
 * @version 1.0
 * @date 2019/9/11 15:24
 * @Description 根据时间筛选活跃用户
 */
@Data
public class SelectUserInfo {

   /**
    * 部门
    */
   private String dept ;

   /**
    * 工号
    */
   private String empNo;

   /**
    * 姓名
    */
   private String userName;

   private Long count;


}
