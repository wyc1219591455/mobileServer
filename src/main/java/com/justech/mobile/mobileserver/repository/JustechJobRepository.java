package com.justech.mobile.mobileserver.repository;


import com.justech.mobile.mobileserver.entity.JustechJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustechJobRepository extends JpaRepository<JustechJob,String> {

}
