package hgrx.async.handle;

import hgrx.async.EventHandler;
import hgrx.async.EventModel;
import hgrx.async.EventType;
import hgrx.bean.Message;
import hgrx.bean.User;
import hgrx.dto.ArticleDetailVO;
import hgrx.service.BaseService;
import hgrx.service.MessageService;
import hgrx.util.MyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class LikeHandler implements EventHandler {

    private Log log = LogFactory.getLog(LikeHandler.class);

    @Autowired
    MessageService messageService;

    @Autowired
    BaseService baseService;

    @Autowired
    BaseService userService;

    @Override
    public void doHandle(EventModel model) {
        ArticleDetailVO advo = baseService.getAdvoById(Long.valueOf(model.getExt("articleId")));
        Message message = new Message();
        message.setFromId(MyUtils.SYSTEM_USERID);
        message.setToId((advo.getUserId()));    //发给文章的所有人
        message.setCreatedDate(System.currentTimeMillis());
        User user = userService.getUserById(model.getActorId());

        message.setContent("用户[ " + user.getNickname() + " ]收藏了你的文章:[ " + advo.getTitle() + " ]");
        log.debug("LikeHandle收到了事件:[ " + model + " ]");
        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Collections.singletonList(EventType.LIKE);
    }
}
