package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepstitory extends JpaRepository<Application,Integer> {
    @Query(value = " select * from application where block_id = :blockId and  IFNULL(`status`,'1') <> '0' order by sort desc,create_time asc",nativeQuery = true)
    public List<Application> getApplicationsByBlockId(@Param("blockId") Integer blockId);
}
