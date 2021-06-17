package com.justech.mobile.mobileserver.mapper.sqlserver;


import com.justech.mobile.mobileserver.entity.HumanDtl;
import com.justech.mobile.mobileserver.entity.MealDtl;
import com.justech.mobile.mobileserver.entity.MealUpdateCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: yuchao.wang
 * @Date: 2020/12/18
 * @Description :
 */
@Mapper
public interface EmpMapper {





    //  通过工号查询存在的人员
    Integer findEmpByUserNameList(MealDtl mealDtl);

    void insertMealDtl(List<MealDtl> mealDtls);

    HumanDtl findEmpHuman(String dinerID);

    void deleteMealDtl(MealUpdateCriteria mealUpdateCriteria);


}
