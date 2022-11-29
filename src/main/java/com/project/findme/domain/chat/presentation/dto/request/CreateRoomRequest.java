package com.project.findme.domain.chat.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {

    private String id;

    private String roomName;

    private String imageUrl;

}
