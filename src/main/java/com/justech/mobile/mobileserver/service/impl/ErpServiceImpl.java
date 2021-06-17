package com.justech.mobile.mobileserver.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.justech.mobile.mobileserver.entity.HumanDtl;
import com.justech.mobile.mobileserver.entity.MealDtl;
import com.justech.mobile.mobileserver.entity.MealDtlCriteria;
import com.justech.mobile.mobileserver.entity.MealUpdateCriteria;
import com.justech.mobile.mobileserver.mapper.sqlserver.EmpMapper;
import com.justech.mobile.mobileserver.service.ErpService;
import com.justech.mobile.mobileserver.util.AesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 * @website https://docs.auauz.net
 * @Description:插入报餐
 * @Auther: yuchao.wang
 * @Date: 2021/5/7
 */
@Service
@RequiredArgsConstructor
public class ErpServiceImpl implements ErpService {

    private final EmpMapper empMapper;

    @Autowired
    private AesUtil aesUtil;

    @Override
    public void InsertMeal(MealDtlCriteria mealDtlList) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        List<MealDtl> insertList=new ArrayList<>();
        String dinnerId=aesUtil.decrypt(mealDtlList.getDinerUserName());
        HumanDtl humanDtl=empMapper.findEmpHuman(dinnerId);
        for(int i=0;i<mealDtlList.getDinerDate().size();i++) {
            MealUpdateCriteria mealUpdateCriteria=new MealUpdateCriteria();
            mealUpdateCriteria.setDinerDate(mealDtlList.getDinerDate().get(i));
            mealUpdateCriteria.setDinerID(dinnerId);
            empMapper.deleteMealDtl(mealUpdateCriteria);
            for (int j = 0; j < mealDtlList.getDinerType().size(); j++) {
                MealDtl mealDtl=new MealDtl();
                mealDtl.setDinerID(dinnerId);
                mealDtl.setDinerName(humanDtl.getName());
                mealDtl.setDinerDeptID(humanDtl.getDeptID());
                mealDtl.setDinerDeptName(humanDtl.getDeptName());
                mealDtl.setDinerDate(mealDtlList.getDinerDate().get(i));
                mealDtl.setDinerType(mealDtlList.getDinerType().get(j));
                Integer countMeal = empMapper.findEmpByUserNameList(mealDtl);
                if (countMeal == 0) {
                    insertList.add(mealDtl);
                }
            }
        }
        if (ObjectUtil.isNotEmpty(insertList)) {
            empMapper.insertMealDtl(insertList);

        }
    }



    @Override
    public void UpdateMeal(MealUpdateCriteria mealDtlList) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        List<MealDtl> insertList=new ArrayList<>();
        String dinnerId=aesUtil.decrypt(mealDtlList.getDinerUserName());
        HumanDtl humanDtl=empMapper.findEmpHuman(dinnerId);
        mealDtlList.setDinerID(dinnerId);
        empMapper.deleteMealDtl(mealDtlList);
        for (int j = 0; j < mealDtlList.getDinerType().size(); j++) {
                MealDtl mealDtl=new MealDtl();
                mealDtl.setDinerID(dinnerId);
                mealDtl.setDinerName(humanDtl.getName());
                mealDtl.setDinerDeptID(humanDtl.getDeptID());
                mealDtl.setDinerDeptName(humanDtl.getDeptName());
                mealDtl.setDinerDate(mealDtlList.getDinerDate());
                mealDtl.setDinerType(mealDtlList.getDinerType().get(j));
                insertList.add(mealDtl);
            }

        if (ObjectUtil.isNotEmpty(insertList)) {
            empMapper.insertMealDtl(insertList);

        }
    }
}
