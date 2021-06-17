package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.To.JustechDeptVo;
import com.justech.mobile.mobileserver.To.UserOfErpEntity;
import com.justech.mobile.mobileserver.entity.JustechUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 15:13 2019/10/21
 * @Description : TODO
 * @Version: 1.0
 */
@Repository
public interface JustechUserRepository extends JpaRepository<JustechUser, String> {

    //@Query(value = "select user.FEmpName,user.FEmpID,user.FEmail,user.FUserMobile,user.FExt from user where user.emp_id = ?1")
    JustechUser findTopByEmpId(String empid);

    /*@Query(value = "select new com.justech.mobile.mobileserver.entity(user.FEmpName,user.FEmpID,user.FEmail,user.FUserMobile,user.FExt) from user where user.emp_id = empid")
    UserOfErpEntity myQuery(@Param("empid") String empid);*/


}
