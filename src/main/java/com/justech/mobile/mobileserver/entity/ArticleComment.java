package com.justech.mobile.mobileserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName ArticleComment
 * @Author: xiaofeng.yang
 * @Date: Create in 15:49 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */

@Entity
@Table(name = "comment_content")
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "content")
    private String content;
    @Column(name = "result")
    private Integer result;
    @Column(name = "article_id")
    private Integer articleId;
    @Column(name = "creater")
    private String creater;
    @Column(name = "anonymous")
    private Integer anonymous;
    @Column(name = "create_time")
    private Date createTime;

    public ArticleComment() {

    }

    public ArticleComment(Integer id, String content, Integer result, Integer articleId, String creater, Integer anonymous, Date createTime) {
        this.id = id;
        this.content = content;
        this.result = result;
        this.articleId = articleId;
        this.creater = creater;
        this.anonymous = anonymous;
        this.createTime = createTime;
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
