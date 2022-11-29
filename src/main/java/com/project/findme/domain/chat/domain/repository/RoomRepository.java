package com.project.findme.domain.chat.domain.repository;

import com.project.findme.domain.chat.domain.Room;
import com.project.findme.domain.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {

//    @Query("select room from Room room, RoomUser roomUser where room.id = roomUser.room.id and roomUser.user =: userA")
//    Optional<Room> findRoomByRoomUsers(User userA, User userB);

}
