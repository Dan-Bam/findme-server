package com.project.findme.domain.chat.presentation;

import com.project.findme.domain.chat.presentation.dto.request.CreateRoomRequest;
import com.project.findme.domain.chat.presentation.dto.response.ChatResponse;
import com.project.findme.domain.chat.presentation.dto.response.RoomResponse;
import com.project.findme.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Void> createRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        chatService.createRoom(createRoomRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{roomId}")
    public ResponseEntity<List<ChatResponse>> findAllChats(@PathVariable Long roomId) {
        return new ResponseEntity<>(chatService.findAllChats(roomId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> findAllMyRooms() {
        return new ResponseEntity<>(chatService.findAllRooms(), HttpStatus.OK);
    }

}
