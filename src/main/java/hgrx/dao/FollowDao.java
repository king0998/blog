package hgrx.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by HGRX on 2017/6/11
 */
@Repository
public interface FollowDao {

    @Select("SELECT count(*) FROM follow WHERE main_user_id = #{targetId} AND follower_id = ${selfId}")
    boolean hasFollow(@Param("selfId") Long id, @Param("targetId") Long id1);
}
