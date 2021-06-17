package com.justech.mobile.mobileserver.entity;

import com.justech.mobile.mobileserver.util.ListStringConvert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Suggestion
 * @Author: xiaofeng.yang
 * @Date: Create in 14:10 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
@Entity
@Table(name = "suggestion")
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "content")
    private String content;
    @Column(name = "evaluate")
    private Integer evaluate;
    @Column(name = "image_uris")
    @Convert(converter = ListStringConvert.class)
    private List<String> imageUris;
    @Column(name = "suggester")
    private String suggester;
    @Column(name = "create_time")
    private Date createTime;

    public Suggestion() {

    }

    public Suggestion(String content, Integer evaluate, List<String> imageUris, String suggester, Date createTime) {
        this.content = content;
        this.evaluate = evaluate;
        this.imageUris = imageUris;
        this.suggester = suggester;
        this.createTime = createTime;
    }

    public Suggestion(String content, Integer evaluate, String suggester, Date createTime) {
        this.content = content;
        this.evaluate = evaluate;
        this.suggester = suggester;
        this.createTime = createTime;
    }

    public List<String> getImageUris() {
        return imageUris;
    }

    public void setImageUris(List<String> imageUris) {
        this.imageUris = imageUris;
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

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }



    public String getSuggester() {
        return suggester;
    }

    public void setSuggester(String suggester) {
        this.suggester = suggester;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
