package com.justech.mobile.mobileserver.To;

/**
 * @ClassName QueryAticleDto
 * @Author: xiaofeng.yang
 * @Date: Create in 9:54 2019/7/30
 * @Description : TODO
 * @Version: 1.0
 */
public class QueryAticleDto {
    private Integer channelId;
    private Integer categoryId;
    private Integer status;
    private Integer pageSize=10;
    private Integer pageNo=1;

    public QueryAticleDto() {

    }

    public QueryAticleDto(Integer channelId, Integer categoryId, Integer status, Integer pageSize, Integer pageNo) {
        this.channelId = channelId;
        this.categoryId = categoryId;
        this.status = status;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
