package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName BlockRepstitory
 * @Author: xiaofeng.yang
 * @Date: Create in 11:39 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
public interface BlockRepstitory extends JpaRepository<Block,Integer> {
    @Query(value = "select * from block where name = :name limit  1 ",nativeQuery = true)
    public Block getBlockByName(String name);
}
