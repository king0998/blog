package hgrx.dao;

import hgrx.bean.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HGRX on 2017/6/12
 */
@Repository
public interface CommentDao {

    String INSERT_PAR = " entity_type, entity_id, nickname, email, content, created_date ";


    @Insert("INSERT INTO comment (" + INSERT_PAR + ") VALUES (#{entityType},#{entityId},#{nickname},#{email},#{content},#{createdDate})")
    void addComment(Comment comment);

    @Select("SELECT " + INSERT_PAR +
            "FROM comment WHERE entity_type = #{type} AND entity_id = #{id}")
    List<Comment> listCommentWithTypeAndId(@Param("type") int entityType,
                                           @Param("id") Long entityId);

    @Select("SELECT count(*) FROM comment WHERE entity_type = 2 AND entity_id = #{id}")
    Integer listMessageNum(Long id);

    @Select("SELECT count(*) FROM comment , article  WHERE article.id = comment.entity_id AND comment.entity_type = 1 AND article.user_id = #{id}")
    Integer getCommentNum(Long id);
}
