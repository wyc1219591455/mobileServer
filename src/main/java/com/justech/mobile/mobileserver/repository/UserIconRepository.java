package com.justech.mobile.mobileserver.repository;


import com.justech.mobile.mobileserver.entity.UserIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIconRepository extends JpaRepository<UserIcon, String> {
    @Query(value = "select icon from UserIcon icon join JustechUser user on icon.userId=user.id and user.empId=:empNo where 1=1")
    public List<UserIcon> findAllByEmpNo(@Param("empNo") String empNo);
}
