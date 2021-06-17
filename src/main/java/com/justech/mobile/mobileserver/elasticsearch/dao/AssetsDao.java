package com.justech.mobile.mobileserver.elasticsearch.dao;


import com.justech.mobile.mobileserver.elasticsearch.entity.AssetsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetsDao extends ElasticsearchRepository<AssetsEntity,String> {
    public List<AssetsEntity> findAllByKeeperID(String keeperId);
}
