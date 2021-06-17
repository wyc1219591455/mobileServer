package com.justech.mobile.mobileserver.service;

import com.justech.mobile.mobileserver.entity.Application;
import com.justech.mobile.mobileserver.entity.Block;
import com.justech.mobile.mobileserver.repository.ApplicationRepstitory;
import com.justech.mobile.mobileserver.repository.BlockRepstitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ApplicationService
 * @Author: xiaofeng.yang
 * @Date: Create in 11:14 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepstitory applicationRepstitory;
    @Autowired
    private BlockRepstitory blockRepstitory;
    /*
     * @Name getApplicationList
     * @Author xiaofeng.yang
     * @Description //TODO
     * @Date 12:00 2019/7/16
     * @Param [blockId]
     * @return java.util.List<Application>
     **/
    public List<Application> getApplicationList(Integer blockId){
        return applicationRepstitory.getApplicationsByBlockId(blockId);
    }
    /*
     * @Name getBlock
     * @Author xiaofeng.yang
     * @Description //TODO
     * @Date 12:00 2019/7/16
     * @Param [name]
     * @return Block
     **/
    public Block getBlock(String name){
        return blockRepstitory.getBlockByName(name);
    }

}
