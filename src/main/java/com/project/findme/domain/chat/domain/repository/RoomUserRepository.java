package com.project.findme.domain.chat.domain.repository;

import com.project.findme.domain.chat.domain.RoomUser;
import com.project.findme.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomUserRepository extends JpaRepository<RoomUser, Long> {
    List<RoomUser> findRoomUsersByUser(User user);
}
