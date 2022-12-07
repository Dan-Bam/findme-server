package com.project.findme.domain.chat.facade;

import com.corundumstudio.socketio.SocketIOClient;
import com.project.findme.domain.chat.domain.Room;
import com.project.findme.domain.chat.domain.RoomUser;
import com.project.findme.domain.chat.domain.repository.RoomRepository;
import com.project.findme.domain.chat.domain.repository.RoomUserRepository;
import com.project.findme.domain.chat.exception.ChattingRoomNotFoundException;
import com.project.findme.domain.chat.presentation.dto.response.RoomResponse;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.facade.UserFacade;
import com.project.findme.global.webSocket.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class RoomFacade {

    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;
    private final ChatFacade chatFacade;
    private final UserFacade userFacade;

    @Transactional(rollbackFor = Exception.class)
    public void saveRoom(User userA, User userB, String roomName, String imageUrl) {

        Room room = roomRepository.save(Room
                .builder()
                .lastChat(Room.LastChat.builder().lastMessage("채팅방이 생성되었습니다").lastSentAt(LocalDateTime.now()).build())
                .roomName(roomName)
                .imageUrl(imageUrl)
                .build());

        List<RoomUser> roomUsers = new ArrayList<>();

        roomUsers.add(RoomUser.builder()
                .user(userA)
                .room(room)
                .build());

        roomUsers.add(RoomUser.builder()
                .user(userB)
                .room(room)
                .build());

        roomUserRepository.saveAll(roomUsers);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRoom(Long roomId) {
        chatFacade.deleteChat(roomId);
        roomRepository.delete(findRoomByRoomId(roomId));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Room findRoomByRoomId(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(ChattingRoomNotFoundException::new);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<RoomResponse> findAllMyRoom() {
        User user = userFacade.currentUser();

        return roomUserRepository.findRoomUsersByUser(user)
                .stream()
                .map(RoomResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Room getCurrentRoom(SocketIOClient client) {
        return findRoomByRoomId(SocketUtil.getRoomId(client));
    }

}
