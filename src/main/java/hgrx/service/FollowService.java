package hgrx.service;

import hgrx.bean.User;
import hgrx.dao.FollowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HGRX on 2017/6/11
 */
@Service
public class FollowService {

    @Autowired
    FollowDao followDao;


    /**
     * @param self   自己
     * @param target 要查询的人
     */
    public boolean hasFollow(User self, User target) {
        return followDao.hasFollow(self.getId(), target.getId());
    }
}
