package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.JustechPos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 11:54 2019/10/22
 * @Description : TODO
 * @Version: 1.0
 */
@Repository
public interface JustechPosRepository extends JpaRepository<JustechPos,String> {
}
