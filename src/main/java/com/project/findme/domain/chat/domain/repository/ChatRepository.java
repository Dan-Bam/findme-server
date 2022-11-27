package com.project.findme.domain.chat.domain.repository;

import com.project.findme.domain.chat.domain.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Long> {
    List<Chat> findChatByRoomIdOrderByIdAsc(Long roomId);
}
