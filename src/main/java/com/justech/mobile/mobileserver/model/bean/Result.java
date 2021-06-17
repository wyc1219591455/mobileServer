package com.justech.mobile.mobileserver.model.bean;

import lombok.Data;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 * @date 2019/9/11 15:24
 * @Description 自定义分页对象
 */
@Data
public class Result {

    private List<AccountLogBean> content;

    private Long totalElements;

    private Integer totalPages;

}
