package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.log.DeviceInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 消息es 实体
 */
@Repository
public interface DeviceInfoRepository extends ElasticsearchRepository<DeviceInfo, String> {

    public DeviceInfo findByDeviceId(String deviceId);


}
