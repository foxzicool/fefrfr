package com.example.demo.controller; // 確保此類位於正確的包中

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component // 將此類標記為 Spring 管理的 bean
public class SimpleWebSocketController extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("Received message: " + message.getPayload());
    }
}
