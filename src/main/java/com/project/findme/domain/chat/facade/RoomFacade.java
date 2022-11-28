package com.project.findme.domain.chat.facade;

import com.project.findme.domain.chat.domain.Room;
import com.project.findme.domain.chat.domain.repository.RoomRepository;
import com.project.findme.domain.chat.domain.repository.RoomUserRepository;
import com.project.findme.domain.chat.exception.ChattingRoomNotFoundException;
import com.project.findme.domain.chat.exception.DuplicateChattingRoomException;
import com.project.findme.domain.chat.presentation.dto.response.RoomResponse;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoomFacade {

    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;
    private final UserFacade userFacade;

    @Transactional(rollbackFor = Exception.class)
    public void saveRoom(User userA, User userB, String imageUrl) {

        User user1;
        User user2;

        if(userA.getUserId() < userB.getUserId()) { // UserB가 크면 작은게 user1
            user1 = userA;
            user2 = userB;
        } else {
            user1 = userB;
            user2 = userA;
        }

        checkRoomExist(user1, user2);

        Room room = roomRepository.save(Room.builder()
                        .lastChat(null)
                        .imageUrl(imageUrl)
                        .build());

        room.addRoomUser(user1);
        room.addRoomUser(user2);

    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void checkRoomExist(User userA, User userB) {
        Optional<Room> room = roomRepository.findRoomByRoomUsers(userA, userB);

        if(room.isPresent()) {
            throw new DuplicateChattingRoomException();
        }
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

}
