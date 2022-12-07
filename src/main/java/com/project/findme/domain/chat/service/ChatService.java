package com.project.findme.domain.chat.service;

import com.project.findme.domain.chat.presentation.dto.request.CreateRoomRequest;
import com.project.findme.domain.chat.presentation.dto.request.SendChatRequest;
import com.project.findme.domain.chat.presentation.dto.response.ChatResponse;
import com.project.findme.domain.chat.presentation.dto.response.RoomResponse;

import java.util.List;

public interface ChatService {

    void createRoom(CreateRoomRequest createRoomRequest);
    void deleteRoom(Long roomId);
    List<ChatResponse> findAllChats(Long roomId);
    List<RoomResponse> findAllRooms();

}
