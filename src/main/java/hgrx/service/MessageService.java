package hgrx.service;

import hgrx.bean.Message;
import hgrx.dao.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HGRX on 2017/6/11
 */
@Service
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    SensitiveService sensitiveService;

    public void addMessage(Message message) {
        message.setContent(sensitiveService.filter(message.getContent()));
        messageDAO.addMessage(message);
    }

    public List<Message> getConversationDetail(String conversationId) {
        return messageDAO.getConversationDetail(conversationId);
    }

    public List<Message> getConversationList(Long userId) {
        return messageDAO.getConversationList(userId);
    }

    public int getConversationUnreadCount(Long userId, String conversationId) {
        return messageDAO.getConversationUnreadCount(userId, conversationId);
    }
}
