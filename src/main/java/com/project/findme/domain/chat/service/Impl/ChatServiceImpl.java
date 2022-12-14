package com.project.findme.domain.chat.service.Impl;

import com.project.findme.domain.chat.facade.ChatFacade;
import com.project.findme.domain.chat.facade.RoomFacade;
import com.project.findme.domain.chat.presentation.dto.request.CreateRoomRequest;
import com.project.findme.domain.chat.presentation.dto.response.ChatResponse;
import com.project.findme.domain.chat.presentation.dto.response.RoomResponse;
import com.project.findme.domain.chat.service.ChatService;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final UserFacade userFacade;
    private final RoomFacade roomFacade;
    private final ChatFacade chatFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRoom(CreateRoomRequest createRoomRequest) {
        User userA = userFacade.currentUser();
        User userB = userFacade.findUserById(createRoomRequest.getId());

        roomFacade.saveRoom(userA, userB,createRoomRequest.getRoomName(), createRoomRequest.getImageUrl());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoom(Long roomId) {
        roomFacade.deleteRoom(roomId);
    }

    @Override
    public List<ChatResponse> findAllChats(Long roomId) {
        User user = userFacade.currentUser();
        return chatFacade.findAllChats(roomId, user);
    }

    @Override
    public List<RoomResponse> findAllRooms() {
        return roomFacade.findAllMyRoom();
    }
}
