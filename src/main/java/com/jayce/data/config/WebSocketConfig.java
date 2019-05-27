package com.jayce.data.config;

import com.jayce.data.config.handler.MySocketHandler;
import com.jayce.data.config.interceptor.SocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/*@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired
    private MySocketHandler socketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        //注册处理拦截器,拦截url为socketServer的请求
        //拦截的请求，（注意首先得被servlet拦截到，即要注意web-inf0中的配置）
        registry.addHandler(socketHandler, "/socketConn").addInterceptors(new SocketInterceptor());

        registry.addHandler(socketHandler, "/api/socketConn").addInterceptors(new SocketInterceptor()).withSockJS();
    }
}*/
