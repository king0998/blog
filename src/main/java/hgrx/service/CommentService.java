package hgrx.service;

import com.google.gson.Gson;
import hgrx.async.EventModel;
import hgrx.async.EventProducer;
import hgrx.async.EventType;
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

    @Autowired
    EventProducer eventProducer;

    public void addComment(Comment comment) {

        EventModel eventModel = new EventModel(EventType.COMMENT)
                .setExt("comment", new Gson().toJson(comment));
        eventProducer.fireEvent(eventModel);

        commentDao.addComment(comment);
    }

    public List<Comment> listAllCommentWithTypeAndId(int entityType, Long entityId) {
        return commentDao.listCommentWithTypeAndId(entityType, entityId);
    }
}
