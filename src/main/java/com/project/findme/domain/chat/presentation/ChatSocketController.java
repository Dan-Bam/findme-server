package com.project.findme.domain.chat.presentation;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.project.findme.domain.chat.presentation.dto.request.SendChatRequest;
import com.project.findme.domain.chat.service.ChatSocketService;
import com.project.findme.global.webSocket.property.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatSocketController {

    private final ChatSocketService chatSocketService;

    @OnEvent(SocketProperty.CHATTING)
    public void sendChat(SocketIOClient socketIOClient, SendChatRequest sendChatRequest) {
        chatSocketService.execute(socketIOClient, sendChatRequest);
    }

}
