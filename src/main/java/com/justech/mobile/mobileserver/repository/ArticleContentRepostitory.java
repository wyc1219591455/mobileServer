package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArticleContentRepostitory extends JpaRepository<ArticleContent,Integer> {
    @Query(value = "from ArticleContent where articleId = :articleId")
    public List<ArticleContent> getArticleContentByArticleId(Integer articleId);

}
