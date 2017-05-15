package hgrx.dao;

import hgrx.bean.Article;
import hgrx.bean.Tag;
import hgrx.bean.User;
import hgrx.entity.ArticleDetailVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HGRX on 2017/5/11
 */
@Repository
public interface BaseDao {

    @Select("SELECT id,user_id,title,timestamp,content FROM article WHERE id = #{id}")
    Article getArticleById(Long id);

    @Select("SELECT username FROM user WHERE id = #{id}")
    String getUsernameById(Long id);

    @Select("SELECT count(*) FROM `like` WHERE article_id = #{id}")
    Integer getLikeNumById(Long id);

    @Select("SELECT count(*) FROM star WHERE article_id = #{id}")
    Integer getStarNumById(Long id);

    List<ArticleDetailVO> listAdvoWithoutTagsByUserId(Long id);

    @Select("SELECT name FROM article_tags_link,tag WHERE tag_id = tag.id AND article_id = #{articleId}")
    List<String> listTagsWithArticleId(long id);

    @Select("SELECT id,nickname,intro FROM user WHERE id = #{id}")
    User getUserById(Long id);

    @Select("SELECT id,user_id,intro,name FROM tag WHERE user_id = ${_parameter}")
    List<Tag> listTagsByUserId(Long id);

    @Select("SELECT about FROM about WHERE user_id = #{userId}")
    String getAboutByUserId(Long id);
}