package com.justech.mobile.mobileserver.controller;


import com.justech.mobile.mobileserver.entity.MealDtlCriteria;
import com.justech.mobile.mobileserver.service.ErpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final ErpService erpService;

    @PostMapping
    public void test(@RequestBody MealDtlCriteria mealDtlList) throws Exception {
          erpService.InsertMeal(mealDtlList);
    }

//    @PostMapping("/update")
//    public void updateTest(@RequestBody MealUpdateCriteria mealDtlList) throws Exception {
//        erpService.UpdateMeal(mealDtlList);
//    }
}
