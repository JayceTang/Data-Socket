package com.jayce.data.controller;

import com.jayce.data.config.handler.MySocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/socket")
public class SocketPushController {
    private static final Logger logger = LoggerFactory.getLogger(SocketPushController.class);

    @Autowired
    private MySocketHandler handler;

    @RequestMapping("login")
    public String login(HttpSession session,String name){
        logger.info(name+"登录了");

        session.setAttribute("user", name);

        return "../socketPush/chatroom";
    }

    @ResponseBody
    @RequestMapping("sendMessage")
    public String sendMessage(HttpSession session, String message){
        String name = (String) session.getAttribute("user");
        handler.sendMessageToUsers( name,new TextMessage(name+" : "+message));
        return "success";
    }
}
