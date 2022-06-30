package com.nylgsc.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author rendj
 * @Description 报告进度WebSocket
 * @Date 9:42 2020/12/23
 **/
@Slf4j
@Component
@ServerEndpoint("/ws/report/schedule")
public class EngineeringReportWebSocket {

    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    public static ConcurrentHashMap<String, EngineeringReportWebSocket> webSocketClients = new ConcurrentHashMap<>();
    /**
     *  与某个客户端的连接对话，需要通过它来给客户端发送消息
     */
    private Session session;
    /**
     * 请求唯一标识
     **/
    private String sessionId;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        this.sessionId = session.getId();
        webSocketClients.put(this.sessionId, this);
        log.info("session id {} 连接成功，当前连接数量为：{}", this.sessionId, webSocketClients.size());
    }

    @OnClose
    public void onClose(){
        webSocketClients.remove(this.sessionId);
        log.info("session id {} 退出成功，当前连接人数为：{}", this.sessionId, webSocketClients.size());
    }

    @OnError
    public void onError(Session session, Throwable e) {
        log.error("session id {} 发生错误，当前连接人数为：{}", this.sessionId, webSocketClients.size());
        log.error(e.getMessage(), e);
    }

    @OnMessage
    public void onMessage(String message){
        log.info("session id {} 发来消息：{}", this.sessionId, message);
    }

    public Session getSession(){
        return session;
    }
}