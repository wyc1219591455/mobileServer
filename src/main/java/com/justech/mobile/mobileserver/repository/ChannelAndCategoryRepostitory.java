package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.ChannelAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelAndCategoryRepostitory extends JpaRepository<ChannelAndCategory,Integer> {

    @Query(value = "select  c1 from ChannelAndCategory c join ChannelAndCategory c1 on c.id=c1.pid where c.name = :name and c.level= :levle order by c1.sort desc,c1.updateTime desc ,c1.createTime asc ")
    public List<ChannelAndCategory> getCategoryByChannelName(@Param("name") String name,@Param("levle") int levle);
}
