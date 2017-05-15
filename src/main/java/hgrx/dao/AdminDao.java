package hgrx.dao;

import hgrx.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

}