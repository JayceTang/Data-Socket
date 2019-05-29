package com.jayce.data.controller;

import com.google.gson.Gson;
import com.jayce.data.dto.SignalStatDataDto;
import com.jayce.data.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

@Component
@ServerEndpoint(value = "/websocket")
public class MyWebSocket {
    /** 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;

    /** concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /** 与某个客户端的连接会话，需要通过它来给客户端发送数据 */
    private static Session s;

    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    
    @Autowired
    private StatService statService;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        s = session;
        //  加入set中
        webSocketSet.add(this);
        //  在线数加1
        addOnlineCount();           
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        ses.scheduleAtFixedRate(() -> {
                try {
                    List<SignalStatDataDto> list = statService.findAll();
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    sendMessage(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            },0, 10, TimeUnit.SECONDS);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //  从set中删除
        webSocketSet.remove(this);
        //  在线数减1
        subOnlineCount();           
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //群发消息
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     */
     @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }


     public void sendMessage(String message) throws IOException {
     s.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }


     /**
      * 群发自定义消息
      * */
    public static void sendInfo(String message) throws IOException {
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
