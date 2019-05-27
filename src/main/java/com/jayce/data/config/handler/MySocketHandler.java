package com.jayce.data.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.SocketHandler;

@Service
public class MySocketHandler implements WebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(SocketHandler.class);
    private Map<String,WebSocketSession> users = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Test: after connection");
        String userName = session.getAttributes().get("user").toString();
        if(null!=userName&&!"".equals(userName)){
            users.put(userName, session);
            session.sendMessage(new TextMessage("system："+userName+"连接成功。。。"));
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        logger.error("连接出现错误",throwable);
        users.remove(session.getAttributes().get("user").toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("连接关闭");
        users.remove(session.getAttributes().get("user").toString());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToUsers(String sender,TextMessage message){
        Set<Map.Entry<String, WebSocketSession>> entrySet = users.entrySet();
        for(Map.Entry<String, WebSocketSession> entry : entrySet){
            String userName = "";
            try{
                userName = entry.getKey();
                if(userName==null||userName.equals(sender)){
                    continue;
                }
                WebSocketSession session = entry.getValue();
                session.sendMessage(message);
            }catch(Exception e){
                logger.error("发送信息给"+userName+"失败",e);
            }
        }
    }

    public void sendMessageToUser(String userName,TextMessage message){
        try{
            WebSocketSession session = users.get(userName);
            session.sendMessage(message);
        }catch(Exception e){
            logger.error("发送消息给"+userName+"失败",e);
        }

    }
}
