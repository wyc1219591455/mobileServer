package com.justech.mobile.mobileserver.repository;


import com.justech.mobile.mobileserver.To.JustechDeptVo;
import com.justech.mobile.mobileserver.entity.JustechDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JustechDeptRepository extends JpaRepository<JustechDept, String> {
}
