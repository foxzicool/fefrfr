package com.example.demo.config;

import com.example.demo.controller.SimpleWebSocketController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SimpleWebSocketConfig implements WebSocketConfigurer {

    private final SimpleWebSocketController simpleWebSocketController;

    public SimpleWebSocketConfig(SimpleWebSocketController simpleWebSocketController) {
        this.simpleWebSocketController = simpleWebSocketController;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 在開發環境中，您可以允許所有源以便使用WebSocketKing等工具進行測試
        registry.addHandler(simpleWebSocketController, "/simple").setAllowedOrigins("*");
    }
}
