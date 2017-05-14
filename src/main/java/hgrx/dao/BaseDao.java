package hgrx.dao;

import hgrx.bean.Article;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by HGRX on 2017/5/11
 */
@Repository
public interface BaseDao {

    @Select("SELECT id,user_id,title,tags,timestamp,content FROM article WHERE id = #{id}")
    Article getArticleById(Long id);

    @Select("SELECT username FROM user WHERE id = #{id}")
    String getUsernameById(Long id);

    @Select("SELECT count(*) FROM `like` WHERE article_id = #{id}")
    Integer getLikeNumById(Long id);

    @Select("SELECT count(*) FROM star WHERE article_id = #{id}")
    Integer getStarNumById(Long id);
}
