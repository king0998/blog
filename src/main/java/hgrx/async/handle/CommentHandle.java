package hgrx.async.handle;

import com.google.gson.Gson;
import hgrx.async.EventHandler;
import hgrx.async.EventModel;
import hgrx.async.EventType;
import hgrx.bean.Comment;
import hgrx.bean.Message;
import hgrx.dto.ArticleDetailVO;
import hgrx.service.BaseService;
import hgrx.service.MessageService;
import hgrx.service.UserService;
import hgrx.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by HGRX on 2017/6/12
 */
@Component
public class CommentHandle implements EventHandler {


    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    BaseService baseService;

    @Override
    public void doHandle(EventModel model) {
        Comment comment = new Gson().fromJson(model.getExt("comment"), Comment.class);
        Message m = new Message();

        m.setFromId(MyUtils.SYSTEM_USERID);
        if (comment.getEntityType() == MyUtils.COMMENT_ENTITY_TYPE_ABOUT) {
            m.setToId(comment.getEntityId());
            m.setContent("用户 " + comment.getNickname() + "  给你留言  "
                    + " : " + comment.getContent());
        } else {
            ArticleDetailVO advo = baseService.getAdvoById(comment.getEntityId());
            m.setToId(advo.getUserId());
            m.setContent("用户 [" + comment.getNickname() + "] 评价了你的文章  [ "
                    + advo.getTitle() + " ]: " + comment.getContent());

        }

        m.setCreatedDate(System.currentTimeMillis());
        messageService.addMessage(m);

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Collections.singletonList(EventType.COMMENT);
    }
}
