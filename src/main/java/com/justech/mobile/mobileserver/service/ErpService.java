package com.justech.mobile.mobileserver.service;

import com.justech.mobile.mobileserver.entity.MealDtl;
import com.justech.mobile.mobileserver.entity.MealDtlCriteria;
import com.justech.mobile.mobileserver.entity.MealUpdateCriteria;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ErpService {

    void InsertMeal(MealDtlCriteria mealDtlList) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException;

    void UpdateMeal(MealUpdateCriteria mealDtlList) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException;
}
