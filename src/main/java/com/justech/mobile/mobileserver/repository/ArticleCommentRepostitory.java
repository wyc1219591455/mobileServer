package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCommentRepostitory extends JpaRepository<ArticleComment,Integer> {

    @Query("from ArticleComment  where creater = :userNo and articleId = :articleId")
    public List<ArticleComment> getArticleCommentsByUserAndArtic(@Param("userNo") String userNo,@Param("articleId") int articleId);

}
