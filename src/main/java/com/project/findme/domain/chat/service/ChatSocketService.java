package com.project.findme.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.project.findme.domain.chat.presentation.dto.request.SendChatRequest;

public interface ChatSocketService {

    void execute(SocketIOClient socketIOClient, SendChatRequest sendChatRequest);

}
