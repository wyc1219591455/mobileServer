package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepostitory extends JpaRepository<Account,Integer> {
    @Query(value = "from Account where empno = :emoNo")
    public Account getAccountByEmpno(String emoNo);

}
