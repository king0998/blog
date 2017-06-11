package hgrx.controller;

import hgrx.bean.Message;
import hgrx.bean.User;
import hgrx.bean.ViewObject;
import hgrx.service.AdminService;
import hgrx.service.BaseService;
import hgrx.service.MessageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    BaseService baseService;

    @Autowired
    AdminService adminService;

    private static final Log logger = LogFactory.getLog(MessageController.class);

    @RequestMapping(path = {"/admin/msg/list"}, method = {RequestMethod.GET})
    public String getConversationList(Model model, HttpSession session) {
        User user = getUser(session);
        if (user == null) {
            return "redirect:/login";
        }
        Long localUserId = user.getId();
        List<Message> conversationList = messageService.getConversationList(localUserId);
        List<ViewObject> conversations = new ArrayList<>();
        for (Message message : conversationList) {
            ViewObject vo = new ViewObject();
            vo.set("message", message);
            Long targetId = Objects.equals(message.getFromId(), localUserId) ? message.getToId() : message.getFromId();
            vo.set("user", baseService.getUserById(targetId));
            vo.set("unread", messageService.getConversationUnreadCount(localUserId, message.getConversationId()));
            conversations.add(vo);
        }
        model.addAttribute("conversations", conversations);
        return "admin/message";
    }

    @RequestMapping(path = {"/admin/msg/detail"}, method = {RequestMethod.GET})
    public String getConversationDetail(Model model, @RequestParam("conversationId") String conversationId) {
        try {
            List<Message> messageList = messageService.getConversationDetail(conversationId);
            List<ViewObject> messages = new ArrayList<>();
            for (Message message : messageList) {
                ViewObject vo = new ViewObject();
                vo.set("message", message);
                vo.set("user", baseService.getUserById(message.getFromId()));
                messages.add(vo);
            }
            model.addAttribute("messages", messages);
        } catch (Exception e) {
            logger.error("获取详情失败" + e.getMessage());
        }
        return "admin/message_detail";
    }


    private User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

}
