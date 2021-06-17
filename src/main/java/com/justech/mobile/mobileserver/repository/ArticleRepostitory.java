package com.justech.mobile.mobileserver.repository;

import com.justech.mobile.mobileserver.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleRepostitory extends JpaRepository<Article, Integer>, PagingAndSortingRepository<Article, Integer> {
    @Query(value = "from  Article  where categoryId=:categoryId ",
            countQuery = "select count(id) from Article  where categoryId=:categoryId ")
    public Page<Article> getArticlesByPid(@Param("categoryId") Integer pid, Pageable pageable);

    @Query(value = "select a.*,c.category category_name,c.channel channel_name from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM" +
            " channel_and_category c1 JOIN channel_and_category c2 ON c2.p_id=c1.id  ) c ON a.category_id=c.cid where a.category_id = :categoryId",
            countQuery = "select count(a.id) from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM" +
                    " channel_and_category c1 JOIN channel_and_category c2 ON c2.p_id=c1.id  ) c ON a.category_id=c.cid where a.category_id = :categoryId", nativeQuery = true)
    public Page<Map> getArticlesToMap(@Param("categoryId") Integer pid, Pageable pageable);


    @Query(value = "from  Article  where categoryId=:categoryId and status=:status",
            countQuery = "select count(id) from Article  where categoryId=:categoryId and status=:status")
    public Page<Article> getArticlesByPid(@Param("categoryId") Integer pid, @Param("status") Integer status, Pageable pageable);

    @Query(value = "select a.*,c.category category_name,c.channel channel_name from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM" +
            " channel_and_category c1 JOIN channel_and_category c2 ON c2.p_id=c1.id  ) c ON a.category_id=c.cid where a.category_id = :categoryId and a.status=:status",
            countQuery = "select count(a.id) from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM" +
                    " channel_and_category c1 JOIN channel_and_category c2 ON c2.p_id=c1.id  ) c ON a.category_id=c.cid where a.category_id = :categoryId and a.status=:status", nativeQuery = true)
    public Page<Map> getArticlesToMap(@Param("categoryId") Integer pid, @Param("status") Integer status, Pageable pageable);

    @Query(nativeQuery = true, value = "select a.*,c.category category_name,c.channel channel_name from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM" +
            " channel_and_category c1 JOIN channel_and_category c2 ON c2.p_id=c1.id ) c ON a.category_id=c.cid", countQuery = "select count(a.id) " +
            "from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM channel_and_category c1 JOIN " +
            "channel_and_category c2 ON c2.p_id=c1.id ) c ON a.category_id=c.cid")
    public Page<Map> getArticleAll(Pageable pageable);

    @Query(value = "select a.*,c.category category_name,c.channel channel_name from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM channel_and_category c1 JOIN channel_and_category c2 ON c2.p_id=c1.id  " +
            ") c ON a.category_id=c.cid and a.status=:status",
            countQuery = "select count(a.id) from article a JOIN (SELECT c2.id FROM channel_and_category c1 JOIN channel_and_category c2 ON c2" +
                    ".p_id=c1.id ) c ON a.category_id=c.id and a.status=:status", nativeQuery = true)
    public Page<Map> getArticlesBystatus(@Param("status") Integer status, Pageable pageable);

    @Query(value = "select a.*,c.category category_name,c.channel channel_name from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM channel_and_category c1 JOIN channel_and_category c2 ON c2.p_id=c1.id WHERE " +
            "c1.id=:channel) c ON a.category_id=c.cid and a.status=:status",
            countQuery = "select count(a.id) from article a JOIN (SELECT c2.id FROM channel_and_category c1 JOIN channel_and_category c2 ON c2" +
                    ".p_id=c1.id WHERE c1.id=:channel) c ON a.category_id=c.id and a.status=:status", nativeQuery = true)
    public Page<Map> getArticlesBychannel(@Param("channel") Integer channelId, @Param("status") Integer status, Pageable pageable);

    @Query(value = "select a.*,c.category category_name,c.channel channel_name from article a JOIN (SELECT c2.name category,c1.name channel,c2.id cid FROM channel_and_category c1 JOIN channel_and_category c2 ON c2.p_id=c1.id WHERE " +
            "c1.id=:channel) c ON a.category_id=c.cid ",
            countQuery = "select count(a.id) from article a JOIN (SELECT c2.id FROM channel_and_category c1 JOIN channel_and_category c2 ON c2" +
                    ".p_id=c1.id WHERE c1.id=:channel) c ON a.category_id=c.id", nativeQuery = true)
    public Page<Map> getArticlesBychannel(@Param("channel") Integer channelId, Pageable pageable);

    @Query(value = "select a.* from article a " +
            "join (select count(id) count,article_id from comment_content group by article_id order by count desc limit :num) g " +
            "on a.id=g.article_id join " +
            "(select c1.id from channel_and_category c1  where c1.p_id=:channelId) c on c.id=a.category_id ", nativeQuery = true)
    public List<Article> getHotArticlesByCount(@Param("num") int num, @Param("channelId") int channelId);

    @Query(value = "select a.* from article a join (select c1.id from channel_and_category c1  where c1.p_id = :channelId ) c on c.id=a.category_id", nativeQuery = true)
    public List<Article> getArticlesByCount(@Param("channelId") int channelId);
}
