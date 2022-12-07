package com.project.findme.global.webSocket;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.findme.global.webSocket.config.WebSocketProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WebSocketConfig {

    private final WebSocketProperties webSocketProperties;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        config.setPort(webSocketProperties.getPort());
        config.setOrigin("*");
        config.setSocketConfig(socketConfig);
        return new SocketIOServer(config);
    }

}
