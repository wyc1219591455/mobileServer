package com.justech.mobile.mobileserver.repository;


import com.justech.mobile.mobileserver.entity.UserEvaluation;
import com.justech.mobile.mobileserver.util.UserevaluationIdClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEvaluationRepository extends JpaRepository<UserEvaluation, UserevaluationIdClass> {
    public void deleteAllByVersionNot(Long version);
    public List<UserEvaluation> findAllByTextOrderByWeightDesc(String text);
    public List<UserEvaluation> findAllByTextContainingOrderByWeightDesc(String text);
}
