package com.project.findme.domain.chat.service.Impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.project.findme.domain.chat.domain.Chat;
import com.project.findme.domain.chat.domain.Room;
import com.project.findme.domain.chat.domain.repository.ChatRepository;
import com.project.findme.domain.chat.facade.RoomFacade;
import com.project.findme.domain.chat.presentation.dto.request.SendChatRequest;
import com.project.findme.domain.chat.service.ChatSocketService;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatSocketServiceImpl implements ChatSocketService {

    private final UserFacade userFacade;
    private final RoomFacade roomFacade;
    private final ChatRepository chatRepository;

    @Override
    public void execute(SocketIOClient client, SendChatRequest sendChatRequest) {
        User user = userFacade.currentUser(client);
        Room room = roomFacade.getCurrentRoom(client);

        Chat chat = Chat.builder()
                .user(user)
                .room(room)
                .message(sendChatRequest.getMessage())
                .build();

        room.updateLastMessage(chat);

        chatRepository.save(chat);
    }

}
