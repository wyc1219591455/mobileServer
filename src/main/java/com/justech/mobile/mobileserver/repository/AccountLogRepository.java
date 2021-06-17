package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.log.AccountLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 消息es 实体
 */
@Repository
public interface AccountLogRepository extends ElasticsearchRepository<AccountLog, String> {



}
