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
    public List<JustechDept> findAllByUnUse(String unUse);

    @Query(value = "SELECT new com.justech.mobile.mobileserver.To.JustechDeptVo(dept,tree.pNum)  FROM JustechDept dept LEFT JOIN JustechOrgTree " +
            "tree on dept.id=tree.id WHERE dept.unUse=:unUse")
    public List<JustechDeptVo> findAllByUnUseAndCount(String unUse);

    public List<JustechDept> findAllByParentIdAndOrgId(String pid, String orgid);
    @Query(value = "SELECT new com.justech.mobile.mobileserver.To.JustechDeptVo(dept,tree.pNum)  FROM JustechDept dept LEFT JOIN JustechOrgTree " +
            "tree on dept.id=tree.id WHERE dept.unUse=:unUse and dept.parentId=:pid and dept.orgId=:orgid")
    public List<JustechDeptVo> findAllByParentIdAndOrgIdAndCount(@Param("pid") String pid,@Param("orgid") String orgid,@Param("unUse") String unUse);

    public List<JustechDept> findAllByParentId(String pid);

    @Query(value = "SELECT new com.justech.mobile.mobileserver.To.JustechDeptVo(dept,tree.pNum)  FROM JustechDept dept LEFT JOIN JustechOrgTree " +
            "tree on dept.id=tree.id WHERE dept.unUse=:unUse and dept.parentId=:pid")
    public List<JustechDeptVo> findAllByParentIdAndCount(@Param("pid") String pid,@Param("unUse") String unUse);
}
