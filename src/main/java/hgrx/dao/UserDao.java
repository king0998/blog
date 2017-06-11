package hgrx.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by HGRX on 2017/6/12
 */
@Repository
public interface UserDao {

    @Select("SELECT user_id FROM article WHERE id = #{id}")
    Long getUserIdByArticle(Long id);
}
