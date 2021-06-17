//package com.justech.mobile.mobileserver.service;
//
//import com.justech.mobile.mobileserver.entity.Thirdparty;
//import com.justech.mobile.mobileserver.repository.ThirdpartyRepostitory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * clay
// * 账户服务
// */
//@Service
//public class ThirtpartyService {
//
//    @Autowired
//    private ThirdpartyRepostitory thirdpartyRepostitory;
//
//    public Thirdparty getThirdpartiesByEmpnoAndOpenid(String empNo,String openid) {
//        return thirdpartyRepostitory.getThirdpartiesByEmpnoAndOpenid(empNo,openid);
//    }
//
//
//    public Thirdparty findByEmpnoAndType(String empNo,String type) {
//        return thirdpartyRepostitory.findByEmpnoAndType(empNo,type);
//    }
//
//
//    public void save(Thirdparty thirdparty) {
//         thirdpartyRepostitory.save(thirdparty);
//    }
//
//    public void delete(Thirdparty thirdparty) {
//        thirdpartyRepostitory.delete(thirdparty);
//    }
//}
