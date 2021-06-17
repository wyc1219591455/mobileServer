package com.justech.mobile.mobileserver.model.bean;

import lombok.Data;


/**
 * @author clay
 * @version 1.0
 * @date 2019/9/11 15:24
 * @Description 请求次数分析
 */
@Data
public class RequestAnalyze {

   /**
    * 请求次数
    */
   private Long requestCounts ;


   /**
    * 板块名称
    */
   private String name;



}
