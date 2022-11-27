package com.project.findme.domain.chat.domain.repository;

import com.project.findme.domain.chat.domain.Room;
import com.project.findme.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<Room> findRoomByUserAAndUserB(User userA, User userB);


}
