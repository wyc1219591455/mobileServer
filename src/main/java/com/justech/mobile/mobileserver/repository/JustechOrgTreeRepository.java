package com.justech.mobile.mobileserver.repository;


import com.justech.mobile.mobileserver.entity.JustechOrgTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustechOrgTreeRepository extends JpaRepository<JustechOrgTree,String> {
}
