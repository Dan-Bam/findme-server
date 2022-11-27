package com.project.findme.domain.chat.facade;

import com.project.findme.domain.chat.domain.Chat;
import com.project.findme.domain.chat.domain.Room;
import com.project.findme.domain.chat.domain.repository.ChatRepository;
import com.project.findme.domain.chat.presentation.dto.response.ChatResponse;
import com.project.findme.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatFacade {

    private final ChatRepository chatRepository;

    @Transactional(rollbackFor = Exception.class)
    public Chat saveChat(String message, Room room, User user){
        return chatRepository.save(Chat.builder()
                .message(message)
                .room(room)
                .user(user)
                .build());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ChatResponse> findAllChats(Long roomId, User user) {
        return chatRepository.findChatByRoomIdOrderByIdAsc(roomId)
                .stream()
                .map(chat -> ChatResponse.of(chat, chat.getUser() == user))
                .collect(Collectors.toList());
    }

}
