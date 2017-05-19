package hgrx.dao;

import hgrx.bean.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by HGRX on 2017/5/14
 */
@Repository
public interface AdminDao {

    @Insert("INSERT INTO user (nickname, intro, username, password, email) VALUES " +
            "(#{nickname},#{intro},#{username},#{password},#{email})")
    Boolean addUser(User user);

    @Select("SELECT id,nickname,intro,username,password,email FROM user WHERE username = #{_parameter}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO article (user_id,title,content,timestamp,draft) VALUES (#{userId},#{title},#{content},#{timestamp},1)")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void addArticle(Article article);

    @Insert("INSERT INTO article_tags_link (article_id, tag_id) VALUES (#{articleId},#{tagId})")
    void addTagLink(ArticleTagsLink articleTagsLink);

    @Select("SELECT id FROM tag WHERE name = #{name} AND user_id = #{userId}")
    Long getTagId(Tag tag);

    @Insert("INSERT INTO tag (name,user_id) VALUES (#{name},#{userId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void addTag(Tag tag);

    @Insert("INSERT INTO follow (follower_id, main_user_id) VALUES (#{followerId},#{mainUserId})")
    Boolean addFollow(Follow follow);

    /**
     * map 结构:
     * userId: 123
     * id : 456
     *
     * @param par 参数
     */
    @Delete("DELETE FROM article WHERE id = ${id} AND user_id = #{userId}")
    Boolean deleteArticleByPar(Map<String, Long> par);

    Boolean updateArticle(Map<String, Object> par);

    @Select("SELECT main_user_id as id,nickname,intro FROM follow,user WHERE user.id = main_user_id AND follower_id = #{id}")
    List<User> listFollowingListByUserId(Long id);

    @Select("SELECT follower_id as id,nickname,intro FROM follow,user WHERE user.id = follower_id AND main_user_id = #{id}")
    List<User> listFollowerListByUserId(Long id);
}