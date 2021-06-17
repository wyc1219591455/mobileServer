package com.justech.mobile.mobileserver.log;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @author clay
 * @version 1.0
 * @date 2019/9/5 14:59
 * @Description 设备信息
 */

@Data
@Document(indexName = "deviceinfo", type = "deviceinfo")
public class DeviceInfo {

    @Id
    private String id;

    /**
     * 事件id
     */
    private String deviceId;

    /**
     * 发生时间戳
     */
    private Date createTime;

    /**
     * 最近活跃时间
     */
    private Date lastTime;

    /**
     * 最近活跃时间
     */
    private boolean canUse = true;

}
