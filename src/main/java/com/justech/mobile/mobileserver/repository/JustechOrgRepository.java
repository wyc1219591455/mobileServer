package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.To.JustechOrgVo;
import com.justech.mobile.mobileserver.entity.JustechOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JustechOrgRepository extends JpaRepository<JustechOrg, String> {
    public List<JustechOrg> findAllByUnUse(String unUse);

    public List<JustechOrg> findAllByUnUseOrderByOrdSeq(String unUse);

    public List<JustechOrg> findAllByParentId(String pid);

    @Query(value = "SELECT new com.justech.mobile.mobileserver.To.JustechOrgVo(org.id,org.orgNo,org.orgName,org.orgFullName,org.unUse,org.ordSeq," +
            "org.parentId,tree.pNum)  FROM JustechOrg org LEFT JOIN JustechOrgTree tree on org.id=tree.id WHERE org.unUse=:unUse order by org" +
            ".ordSeq asc ")
    public List<JustechOrgVo> findAllByUnUseAndCount(@Param("unUse") String unUse);


}
