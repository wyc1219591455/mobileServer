package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.PostFunctionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostFunctionTypeRepostitory extends JpaRepository<PostFunctionType, Integer> {
    public List<PostFunctionType> findAllByPId(Integer id);
}
