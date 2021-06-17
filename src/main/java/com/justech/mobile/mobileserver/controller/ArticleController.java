package com.justech.mobile.mobileserver.controller;

import com.justech.mobile.mobileserver.To.ArticleAndChannelDto;
import com.justech.mobile.mobileserver.To.ArticleDTo;
import com.justech.mobile.mobileserver.To.QueryAticleDto;
import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.entity.Article;
import com.justech.mobile.mobileserver.entity.ArticleComment;
import com.justech.mobile.mobileserver.entity.ArticleContent;
import com.justech.mobile.mobileserver.service.ArticleService;
import com.justech.mobile.mobileserver.util.CumstonException;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName ArticleController
 * @Author: xiaofeng.yang
 * @Date: Create in 11:14 2019/7/9
 * @Description : TODO
 * @Version: 1.0
 */
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /*
     * @Name getArticles
     * @Author xiaofeng.yang
     * @Description 获取文章列表接口
     * @Date 13:50 2019/7/9
     * @Param [id, pageNo, pageSize]
     * @return java.lang.Object
     **/
    @RequestMapping(value = "/categories/{id}/articles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Page<Article>> getArticles(@PathVariable("id") Integer id, @RequestParam(value = "pageNo", required = false,
            defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if (pageNo < 1) pageNo = 1;
        Page<Article> page = articleService.getArticle(id, pageSize, pageNo - 1);
        return new Response<Page<Article>>().of(ResultEnum.SUCCESS, page);
    }

    /*
     * @Name getArticles
     * @Author xiaofeng.yang
     * @Description 查询文章列表
     * @Date 9:57 2019/7/30
     * @Param [queryAticleDto]
     * @return com.justech.mobile.mobileserver.common.Response<org.springframework.data.domain.Page<Article>>
     **/
    @PostMapping(value = "/articleList", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<Page<ArticleAndChannelDto>> getArticles(@RequestBody QueryAticleDto queryAticleDto) {
        Page<ArticleAndChannelDto> page =null;
        //什么都不传
        if (queryAticleDto.getChannelId()==null&&queryAticleDto.getCategoryId()==null&&queryAticleDto.getStatus()==null)
            page=articleService.getArticleAll(queryAticleDto.getPageSize(),queryAticleDto.getPageNo()-1);
        if(queryAticleDto.getChannelId()==null&&queryAticleDto.getCategoryId()==null&&queryAticleDto.getStatus()!=null)
            page= articleService.getArticleAllBystatus(queryAticleDto.getPageSize(),queryAticleDto.getPageNo()-1,queryAticleDto.getStatus());
        //传分类不传状态检索
        if (queryAticleDto.getCategoryId()!=null&&queryAticleDto.getStatus()==null)
             page = articleService.getArticleToArticleAndChannelDto(queryAticleDto.getCategoryId(), queryAticleDto.getPageSize(), queryAticleDto.getPageNo() - 1);
        //按照分类状态查询
        if (queryAticleDto.getCategoryId()!=null&&queryAticleDto.getStatus()!=null)
             page=articleService.getArticleToArticleAndChannelDto(queryAticleDto.getCategoryId(), queryAticleDto.getPageSize(), queryAticleDto.getPageNo() - 1,queryAticleDto.getStatus());
        //按照频道查询
        if (queryAticleDto.getChannelId()!=null&&queryAticleDto.getCategoryId()==null&&queryAticleDto.getStatus()==null)
            page=articleService.getArticleByChannel(queryAticleDto.getChannelId(),queryAticleDto.getPageSize(),queryAticleDto.getPageNo()-1);
        //按照频道状态查询
        if (queryAticleDto.getChannelId()!=null&&queryAticleDto.getCategoryId()==null&&queryAticleDto.getStatus()!=null)
            page=articleService.getArticleByChannel(queryAticleDto.getChannelId(),queryAticleDto.getPageSize(),queryAticleDto.getPageNo()-1,queryAticleDto.getStatus());
          return new Response<Page<ArticleAndChannelDto>>().of(ResultEnum.SUCCESS, page);
    }


    /*
     * @Name getArticlesContent
     * @Author xiaofeng.yang
     * @Description 获取文章内容
     * @Date 15:47 2019/7/9
     * @Param [articlesId]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    public String getArticlesContent(@PathVariable("id") Integer articlesId) {
        ArticleContent articleContent = articleService.getContent(articlesId);
        if (articleContent != null) return articleContent.getContent();
        return null;
    }

    @RequestMapping(value = "/articles",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getArticlesInfo(@RequestParam("id") int id){

        return new Response().of(ResultEnum.SUCCESS,articleService.getArticle(id));
    }

    /*
     * @Name setArticlesComment
     * @Author xiaofeng.yang
     * @Description 文章评论
     * @Date 8:13 2019/7/15
     * @Param [articleComment]
     * @return com.justech.mobile.mobileserver.common.Response
     **/
    @RequestMapping(value = "/articles/{id}/comment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public Response setArticlesComment(@PathVariable("id") Integer id, @RequestBody ArticleComment articleComment) {
        if (articleComment.getCreater().isEmpty() || articleComment.getResult() == null)
            return new Response().of(ResultEnum.INVALIDPARAM, null);
        try {
            articleComment.setArticleId(id);
            articleComment.setCreateTime(new Date());
            articleService.setComment(articleComment);
        } catch (CumstonException e) {
            e.printStackTrace();
            return new Response().of(ResultEnum.INVALIDPARAM, e.getMessage(), null);
        }
        return new Response().of(ResultEnum.SUCCESS, null);
    }

    @RequestMapping(value = "/hotArticles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getHotArticles(@RequestParam(value = "count", defaultValue = "1") int count, @RequestParam(value = "channelId",
            required = true) int channelId) {
        return new Response().of(ResultEnum.SUCCESS, articleService.getHotArticles(count, channelId));
    }

    /*
     * @Name addArticles
     * @Author xiaofeng.yang
     * @Description 添加文章
     * @Date 9:55 2019/7/25
     * @Param [icon, articleDTo]
     * @return com.justech.mobile.mobileserver.common.Response
     **/
    @RequestMapping(value = "/article", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response addArticles(@RequestParam(value = "icon",required = false) MultipartFile icon, ArticleDTo articleDTo) {
        articleDTo.setIcon(icon);
        try {
            return new Response().of(ResultEnum.SUCCESS, articleService.addArticle(articleDTo));
        } catch (IOException e) {
            e.printStackTrace();
            return new Response().of(ResultEnum.BAD, e.getMessage());
        } catch (CumstonException e) {
            e.printStackTrace();
            return new Response().of(ResultEnum.BAD, e.getMessage());
        }

    }
    @RequestMapping(value = "/article/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response addArticles(@PathVariable("id") int id,@RequestParam(value = "icon",required = false) MultipartFile icon, ArticleDTo articleDTo) {
        if (icon!=null&&!icon.isEmpty()) {
            articleDTo.setIcon(icon);
        }
        try {
            return new Response().of(ResultEnum.SUCCESS, articleService.updateArticle(articleDTo,id));
        } catch (IOException e) {
            e.printStackTrace();
            return new Response().of(ResultEnum.BAD, e.getMessage());
        } catch (CumstonException e) {
            e.printStackTrace();
            return new Response().of(ResultEnum.BAD, e.getMessage());
        }

    }

}
