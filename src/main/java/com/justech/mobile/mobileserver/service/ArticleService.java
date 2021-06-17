package com.justech.mobile.mobileserver.service;

import com.justech.mobile.mobileserver.To.ArticleAndChannelDto;
import com.justech.mobile.mobileserver.To.ArticleDTo;
import com.justech.mobile.mobileserver.To.ImageTo;
import com.justech.mobile.mobileserver.entity.Article;
import com.justech.mobile.mobileserver.entity.ArticleComment;
import com.justech.mobile.mobileserver.entity.ArticleContent;
import com.justech.mobile.mobileserver.entity.ChannelAndCategory;
import com.justech.mobile.mobileserver.repository.ArticleCommentRepostitory;
import com.justech.mobile.mobileserver.repository.ArticleContentRepostitory;
import com.justech.mobile.mobileserver.repository.ArticleRepostitory;

import com.justech.mobile.mobileserver.repository.ChannelAndCategoryRepostitory;
import com.justech.mobile.mobileserver.util.CumstonException;
import com.justech.mobile.mobileserver.util.MapToBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ArticleService
 * @Author: xiaofeng.yang
 * @Date: Create in 11:59 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleRepostitory articleRepostitory;
    @Autowired
    private ArticleContentRepostitory articleContentRepostitory;
    @Autowired
    private ArticleCommentRepostitory commentRepostitory;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private ChannelAndCategoryRepostitory channelAndCategoryRepostitory;

    private ExecutorService executorService= Executors.newSingleThreadExecutor();

    /**
     * @return org.springframework.data.domain.Page<Article>
     * @Name getArticle
     * @Author xiaofeng.yang
     * @Description //获取文章列表
     * @Date 13:49 2019/7/9
     * @Param [categoryId, pageSize, pageNo]
     **/
    public Page<Article> getArticle(Integer categoryId, Integer pageSize, Integer pageNo) {
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "updateTime")).and(new Sort(Sort.Direction.ASC,
                "createTime"));
        return articleRepostitory.getArticlesByPid(categoryId, PageRequest.of(pageNo, pageSize, sort));
    }
    public Page<ArticleAndChannelDto> getArticleToArticleAndChannelDto(Integer categoryId, Integer pageSize, Integer pageNo) {
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "update_time")).and(new Sort(Sort.Direction.ASC,
                "create_time"));
        return articleRepostitory.getArticlesToMap(categoryId, PageRequest.of(pageNo, pageSize, sort)).map(this::convertToArticleAndChannelDto);
    }

    public Page<Article> getArticle(Integer pageSize, Integer pageNo){
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "updateTime")).and(new Sort(Sort.Direction.ASC,
                "createTime"));
       return articleRepostitory.findAll(PageRequest.of(pageNo, pageSize, sort));
    }
    public Page<Article> getArticle(Integer categoryId, Integer pageSize, Integer pageNo,Integer status){
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "updateTime")).and(new Sort(Sort.Direction.ASC,
                "createTime"));
        return articleRepostitory.getArticlesByPid(categoryId, status,PageRequest.of(pageNo, pageSize, sort));
    }
    public Page<ArticleAndChannelDto> getArticleToArticleAndChannelDto(Integer categoryId, Integer pageSize, Integer pageNo,Integer status){
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "update_time")).and(new Sort(Sort.Direction.ASC,
                "create_time"));
        return articleRepostitory.getArticlesToMap(categoryId, status,PageRequest.of(pageNo, pageSize, sort)).map(this::convertToArticleAndChannelDto);
    }
    public Page<ArticleAndChannelDto> getArticleByChannel(Integer channelId, Integer pageSize, Integer pageNo,Integer status){
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "update_time")).and(new Sort(Sort.Direction.ASC,
                "create_time"));
        return articleRepostitory.getArticlesBychannel(channelId,status,PageRequest.of(pageNo, pageSize, sort)).map(this::convertToArticleAndChannelDto);
    }
    public Page<ArticleAndChannelDto> getArticleByChannel(Integer channelId, Integer pageSize, Integer pageNo){
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "update_time")).and(new Sort(Sort.Direction.ASC,
                "create_time"));
        return articleRepostitory.getArticlesBychannel(channelId,PageRequest.of(pageNo, pageSize, sort)).map(this::convertToArticleAndChannelDto);
    }
    public Page<ArticleAndChannelDto> getArticleAll(Integer pageSize, Integer pageNo){
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "update_time")).and(new Sort(Sort.Direction.ASC,
                "create_time"));
        Page<ArticleAndChannelDto> page= articleRepostitory.getArticleAll(PageRequest.of(pageNo, pageSize, sort)).map(this::convertToArticleAndChannelDto);
        return page;
//        return articleRepostitory.getArticleAll(PageRequest.of(pageNo, pageSize, sort));
    }
    public Page<ArticleAndChannelDto> getArticleAllBystatus(Integer pageSize, Integer pageNo,Integer status){
        Sort sort = new Sort(Sort.Direction.DESC, "sort").and(new Sort(Sort.Direction.DESC, "update_time")).and(new Sort(Sort.Direction.ASC,
                "create_time"));
        Page<ArticleAndChannelDto> page= articleRepostitory.getArticlesBystatus(status,PageRequest.of(pageNo, pageSize, sort)).map(this::convertToArticleAndChannelDto);
        return page;
//        return articleRepostitory.getArticleAll(PageRequest.of(pageNo, pageSize, sort));
    }
    private ArticleAndChannelDto convertToArticleAndChannelDto(Map map){
        ArticleAndChannelDto articleAndChannelDto=new ArticleAndChannelDto();
        MapToBeanUtil.transMap2HiberanteBean(map,articleAndChannelDto);
        return articleAndChannelDto;
    }

    /**
     * @return ArticleContent
     * @Name getContent
     * @Author xiaofeng.yang
     * @Description 获取文章内容
     * @Date 15:40 2019/7/9
     * @Param [articleId]
     **/
    public ArticleContent getContent(Integer articleId) {
        List<ArticleContent> articleContents = articleContentRepostitory.getArticleContentByArticleId(articleId);
        if (articleContents.isEmpty()) return null;
        return articleContents.get(0);
    }
    public Map<String,Object> getArticle(int id){
        Article article=articleRepostitory.findById(id).get();
        ArticleContent articleContent=this.getContent(id);
        ChannelAndCategory channelAndCategory=channelAndCategoryRepostitory.findById(article.getCategoryId()).get();
        Map<String,Object> map=new HashMap<>();
        map.put("contentId",articleContent.getId());
        map.put("content",articleContent.getContent());
        map.put("channel",channelAndCategory.getPid());
        map.putAll(BeanMap.create(article));
        return map ;
    }
    /*
     * @Name setComment
     * @Author xiaofeng.yang
     * @Description 保存文章评论内容
     * @Date 17:02 2019/7/11
     * @Param [articleComment]
     * @return void
     **/
    public void setComment(ArticleComment articleComment) throws CumstonException {
        if (!commentRepostitory.getArticleCommentsByUserAndArtic(articleComment.getCreater(), articleComment.getArticleId()).isEmpty())
            throw new CumstonException("Repeated comments");
        commentRepostitory.save(articleComment);
    }

    /*
     * @Name getComment
     * @Author xiaofeng.yang
     * @Description 查找个人文章评论内容
     * @Date 17:02 2019/7/11
     * @Param [articleComment]
     * @return ArticleComment
     **/
    public ArticleComment getComment(ArticleComment articleComment) {
        List<ArticleComment> comments = commentRepostitory.getArticleCommentsByUserAndArtic(articleComment.getCreater(),
                articleComment.getArticleId());
        if (comments.isEmpty()) return null;
        return comments.get(0);
    }

    /*
     * @Name getHotArticles
     * @Author xiaofeng.yang
     * @Description 根据评论数量获取最热文章，不足的按照sort不足
     * @Date 17:05 2019/7/16
     * @Param [count]
     * @return java.util.List<Article>
     **/
    public List<Article> getHotArticles(int count, int channelId) {

        List<Article> listHot = articleRepostitory.getHotArticlesByCount(count, channelId);
        if (listHot.size() == count) return listHot;
        List<Article> articles = articleRepostitory.getArticlesByCount(channelId);
        articles.removeAll(listHot);
        int temp = count - listHot.size();
        if(temp>0){
            List<Article> tempRes=new ArrayList<>();
            if (temp<articles.size())
                tempRes = articles.subList(0,temp);
            else
                tempRes = articles.subList(0,articles.size()-1);
            listHot.addAll(tempRes);
        }
        return listHot;
    }

    @Transactional
    public Article addArticle(ArticleDTo articleDTo) throws IOException, CumstonException {
        Article article = new Article(articleDTo);
        ImageTo imageTo = null;
        if (articleDTo.getIcon() != null && !articleDTo.getIcon().isEmpty())
            imageTo = fileUploadService.uploadFile(articleDTo.getIcon().getBytes(), articleDTo.getIcon().getName(),
                    articleDTo.getIcon().getContentType());
        if (imageTo!=null) article.setIcon(imageTo.getPath());
        article.setCreateTime(new Date());
        Article articleWait=  articleRepostitory.save(article);
        ArticleContent articleContent=new ArticleContent(articleDTo.getContent(),articleWait.getId());
        articleContentRepostitory.save(articleContent);
        return articleWait;
    }

    @Transactional
    public Article updateArticle(ArticleDTo articleDTo,int id) throws IOException, CumstonException {
        Article article = new Article(articleDTo);
        ImageTo imageTo = null;
        if (articleDTo.getIcon() != null && !articleDTo.getIcon().isEmpty())
            imageTo = fileUploadService.uploadFile(articleDTo.getIcon().getBytes(), articleDTo.getIcon().getName(),
                    articleDTo.getIcon().getContentType());
        Article oldarticle=articleRepostitory.findById(id).get();
        if (imageTo!=null) {
            article.setIcon(imageTo.getPath());
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    fileUploadService.deleteFile(oldarticle.getIcon());
                }
            });
        }else{
            article.setIcon(oldarticle.getIcon());
        }
        article.setId(id);
        article.setCreateTime(oldarticle.getCreateTime());
        article.setUpdateTime(new Date());
        article.setCreater(oldarticle.getCreater());
        Article articleWait=  articleRepostitory.save(article);
        ArticleContent articleContent=new ArticleContent(articleDTo.getContentId(),articleDTo.getContent(),articleWait.getId());
        articleContentRepostitory.save(articleContent);
        return articleWait;
    }

}
