package hgrx.dao;

import hgrx.bean.Article;
import hgrx.bean.Tag;
import hgrx.bean.User;
import hgrx.dto.ArticleDetailVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by HGRX on 2017/5/11
 */
@Repository
public interface BaseDao {

    @Select("SELECT id,user_id,title,content,timestamp,like_num,star_num FROM article WHERE id = #{id}")
    Article getArticleById(Long id);

    @Select("SELECT article.id,user_id,nickname,title,timestamp,content FROM article,user WHERE user_id = user.id AND article.id = #{id}")
    ArticleDetailVO getAdvoById(Long id);

    @Select("SELECT username FROM user WHERE id = #{id}")
    String getUsernameById(Long id);

    @Select("SELECT count(*) FROM `like` WHERE article_id = #{id}")
    Integer getLikeNumById(Long id);

    @Select("SELECT count(*) FROM star WHERE article_id = #{id}")
    Integer getStarNumById(Long id);

    @Select("SELECT id, user_id, title,  content, timestamp, like_num,  star_num  FROM article WHERE user_id = #{id} ORDER BY timestamp DESC")
    List<ArticleDetailVO> listAdvoWithoutTagsByUserId(Long id);

    @Select("SELECT name FROM article_tags_link,tag WHERE tag_id = tag.id AND article_id = #{articleId}")
    List<String> listTagsWithArticleId(long id);

    @Select("SELECT id,nickname,intro FROM user WHERE id = #{id}")
    User getUserById(Long id);

    @Select("SELECT id,user_id,intro,name FROM tag WHERE user_id = ${_parameter}")
    List<Tag> listTagsByUserId(Long id);

    @Select("SELECT about FROM about WHERE user_id = #{userId}")
    String getAboutByUserId(Long id);

    @Select("SELECT id, user_id, title,  content, timestamp, like_num,  star_num  FROM article ORDER BY timestamp DESC")
    List<ArticleDetailVO> listAllAdvo();

    @Select("SELECT id,title FROM article WHERE user_id = #{userId} ORDER BY timestamp DESC LIMIT 10")
    List<Article> listLatestArticleByUserId(Long userId);

    @Select("SELECT count(*) FROM article_tags_link WHERE tag_id = #{id}")
    Integer getSizeOfTag(Long id);

    @Select("SELECT id, user_id, title,  content, timestamp, like_num,  star_num " +
            " FROM article " +
            " WHERE user_id = #{userId} " +
            "  AND title LIKE concat('%', #{keyword} ,'%') " +
            " ORDER BY timestamp DESC")
    List<ArticleDetailVO> listAdvoByUserIdAndKeyword(Map<String, Object> par);
}
