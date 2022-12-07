package com.project.findme.global.webSocket.exception;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.response.ErrorResponse;
import com.project.findme.global.error.type.ErrorCode;
import com.project.findme.global.webSocket.property.SocketProperty;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class SocketExceptionListener implements ExceptionListener {

    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
        client.disconnect();
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public void onPongException(Exception e, SocketIOClient client) {
        runExceptionHandling(e, client);
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        return false;
    }

    private void runExceptionHandling(Exception e, SocketIOClient client) {
        final ErrorCode errorCode;

        if(e.getCause() instanceof FindmeException) {
            errorCode = ((FindmeException) e.getCause()).getErrorCode();
        } else {
            errorCode = ErrorCode.INTERVAL_SERVER_ERROR;
        }
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(), errorCode.getMsg());

        client.sendEvent(SocketProperty.ERROR, errorResponse);
    }
}
