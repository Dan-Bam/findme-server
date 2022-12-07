package com.project.findme.global.webSocket.util;

import com.corundumstudio.socketio.SocketIOClient;
import com.project.findme.domain.chat.exception.ChattingRoomNotFoundException;
import com.project.findme.domain.chat.exception.InvalidUserException;
import com.project.findme.global.webSocket.property.ClientProperty;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SocketUtil {

    public static String getUserId(SocketIOClient client) {
        if(!client.has(ClientProperty.USER_KEY)) {
            throw new InvalidUserException();
        }
        return client.get(ClientProperty.USER_KEY);
    }

    public static Long getRoomId(SocketIOClient client) {
        if(!client.has(ClientProperty.ROOM_KEY)) {
            throw new ChattingRoomNotFoundException();
        }
        return client.get(ClientProperty.ROOM_KEY);
    }

}
