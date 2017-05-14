package hgrx.dao;

import hgrx.bean.Article;
import hgrx.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by HGRX on 2017/5/14
 */
@Repository
public interface AdminDao {

    @Insert("insert INTO user (nickname, intro, username, password, email) VALUES " +
            "(#{nickname},#{intro},#{username},#{password},#{email})")
    Boolean addUser(User user);

    @Select("SELECT id,nickname,intro,username,password,email FROM user WHERE username = #{_parameter}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO article (user_id,title,content,timestamp,draft) VALUES (#{userId},#{title},#{content},#{timestamp},#{draft})")
    void addArticle(Article article);
}
