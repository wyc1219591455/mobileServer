package com.justech.mobile.mobileserver.service;

import com.justech.mobile.mobileserver.entity.Account;
import com.justech.mobile.mobileserver.repository.AccountRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * clay
 * 账户服务
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepostitory accountRepostitory;

    public Account getAccountByEmpno(String empno){
        return accountRepostitory.getAccountByEmpno(empno);
    }



}
