package hgrx.service;

import hgrx.bean.Comment;
import hgrx.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HGRX on 2017/6/12
 */
@Service
public class CommentService {


    @Autowired
    CommentDao commentDao;

    public void addComment(Comment comment) {
        commentDao.addComment(comment);
    }

    public List<Comment> listAllCommentWithTypeAndId(int entityType, Long entityId) {
        return commentDao.listCommentWithTypeAndId(entityType, entityId);
    }
}
