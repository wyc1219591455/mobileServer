package com.justech.mobile.mobileserver.entity;

import javax.persistence.*;

/**
 * @ClassName ArticleContent
 * @Author: xiaofeng.yang
 * @Date: Create in 15:18 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
@Entity
@Table(name = "article_content")
public class ArticleContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "",nullable = false,unique = true)
    private Integer id;
    @Lob
    @Column(name = "content")
    private String content;
    @Column(name = "article_id")
    private Integer articleId;

    public ArticleContent() {
    }
    public ArticleContent(Integer id, String content, Integer articleId) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
    }

    public ArticleContent(String content, Integer articleId) {
        this.content = content;
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
