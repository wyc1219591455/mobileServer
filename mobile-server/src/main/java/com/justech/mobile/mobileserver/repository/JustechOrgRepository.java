package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.JustechOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JustechOrgRepository extends JpaRepository<JustechOrg, String> {


}
