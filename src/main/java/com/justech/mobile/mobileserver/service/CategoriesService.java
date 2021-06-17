package com.justech.mobile.mobileserver.service;

import com.justech.mobile.mobileserver.entity.ChannelAndCategory;
import com.justech.mobile.mobileserver.repository.ChannelAndCategoryRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoriesService
 * @Author: xiaofeng.yang
 * @Date: Create in 14:59 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class CategoriesService {
    @Autowired
    private ChannelAndCategoryRepostitory channelAndCategoryRepostitory;
    public List<ChannelAndCategory> getCategories(String name, Integer level){
        return channelAndCategoryRepostitory.getCategoryByChannelName(name,level);
    }
    public List<ChannelAndCategory> getCategoriesAll(ChannelAndCategory channelAndCategory){

        ExampleMatcher exampleMatcher=ExampleMatcher.matchingAll().withIgnorePaths("comment","sort");
        Example<ChannelAndCategory> categoryExample=Example.of(channelAndCategory,exampleMatcher);
        return  channelAndCategoryRepostitory.findAll(categoryExample);
    }
}
