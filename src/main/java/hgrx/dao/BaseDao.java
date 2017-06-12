package hgrx.dao;

import hgrx.bean.Article;
import hgrx.bean.Tag;
import hgrx.bean.User;
import hgrx.dto.ArticleDetailVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("SELECT article.id, user_id,nickname, title,  content, timestamp, like_num,  star_num  FROM article,user WHERE article.user_id = user.id ORDER BY timestamp DESC limit 0,10")
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

    @Select("SELECT user.id FROM user,follow WHERE user.id = main_user_id AND follower_id = #{id}")
    List<Integer> listFollowingUser(Long id);

    List<ArticleDetailVO> listFollowingAdvo(List<Integer> following);

    @Select("SELECT id, name  FROM tag")
    List<Tag> listAllTags();

    @Select("SELECT id,user_id,title,content,timestamp FROM article ORDER BY read_num DESC LIMIT 0,20")
    List<ArticleDetailVO> listHotAdvo();

    @Update("UPDATE article SET read_num = read_num + 1 WHERE id = #{id}")
    void updateReadNum(Long id);

    @Select("SELECT count(*) FROM article WHERE user_id = #{id}")
    Integer countArticleNum(@Param("id") Long id);
}
