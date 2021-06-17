package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.PostFunctionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostFunctionUserRepostitory extends JpaRepository<PostFunctionUser,Integer> {
    public List<PostFunctionUser> findAllByTId(int id);
}
