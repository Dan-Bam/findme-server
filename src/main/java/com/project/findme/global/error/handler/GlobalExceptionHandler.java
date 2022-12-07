package com.project.findme.global.error.handler;

import com.project.findme.domain.chat.exception.ChattingRoomNotFoundException;
import com.project.findme.domain.chat.exception.DuplicateChattingRoomException;
import com.project.findme.domain.found.exception.FoundNotFoundException;
import com.project.findme.domain.lost.exception.LostNotFoundException;
import com.project.findme.domain.message.exception.AuthKeyNotMatchException;
import com.project.findme.domain.message.exception.PhoneNumberNotFound;
import com.project.findme.domain.user.exception.*;
import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FindmeException.class)
    public ResponseEntity<ErrorResponse> findmeException(FindmeException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();

        bindingResult.getFieldErrors().forEach(it -> {
            stringBuilder.append(it.getDefaultMessage());
            stringBuilder.append(", ");
        });

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(", "));
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        ErrorResponse errorResponse = new ErrorResponse(stringBuilder.toString(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
