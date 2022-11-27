package com.project.findme.domain.chat.facade;

import com.project.findme.domain.chat.domain.Room;
import com.project.findme.domain.chat.domain.repository.RoomRepository;
import com.project.findme.domain.chat.exception.ChattingRoomNotFoundException;
import com.project.findme.domain.chat.exception.DuplicateChattingRoomException;
import com.project.findme.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoomFacade {

    private final RoomRepository roomRepository;

    @Transactional(rollbackFor = Exception.class)
    public void saveRoom(User userA, User userB, String imageUrl) {
        checkRoomExist(userA, userB);
        roomRepository.save(Room.builder()
                .userA(userA)
                .userB(userB)
                .lastChat(null)
                .imageUrl(imageUrl)
                .build());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void checkRoomExist(User userA, User userB) {
        Optional<Room> room = roomRepository.findRoomByUserAAndUserB(userA, userB);

        if(room.isPresent()) {
            throw new DuplicateChattingRoomException();
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Room findRoomByRoomId(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(ChattingRoomNotFoundException::new);
    }

}
