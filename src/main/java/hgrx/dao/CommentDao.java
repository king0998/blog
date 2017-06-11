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
}
