package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.JustechUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 15:13 2019/10/21
 * @Description : TODO
 * @Version: 1.0
 */
@Repository
public interface JustechUserRepository extends JpaRepository<JustechUser,String> {


    public void deleteAllByVersionNot(@Param("version") Long version);

    public List<JustechUser> findAllByDeptId(String deptid);

    public JustechUser findTopByEmpId(String empid);

    public JustechUser findTopByUserMobileAndUserStatus(String mobile,String status);

}
