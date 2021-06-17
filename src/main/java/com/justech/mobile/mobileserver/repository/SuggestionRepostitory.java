package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepostitory extends JpaRepository<Suggestion,Integer> {
}
