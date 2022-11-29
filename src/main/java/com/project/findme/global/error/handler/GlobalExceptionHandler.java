package com.project.findme.global.error.handler;

import com.project.findme.domain.chat.exception.ChattingRoomNotFoundException;
import com.project.findme.domain.chat.exception.DuplicateChattingRoomException;
import com.project.findme.domain.found.exception.FoundNotFoundException;
import com.project.findme.domain.lost.exception.LostNotFoundException;
import com.project.findme.domain.message.exception.AuthKeyNotMatchException;
import com.project.findme.domain.message.exception.PhoneNumberNotFound;
import com.project.findme.domain.user.exception.*;
import com.project.findme.global.error.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateIdException.class)
    public ResponseEntity<ErrorResponse> DuplicateIdException(DuplicateIdException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> IdNotFoundException(IdNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ErrorResponse> PasswordNotMatchException(PasswordNotMatchException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> RefreshTokenExpiredException(RefreshTokenExpiredException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(AuthKeyNotMatchException.class)
    public ResponseEntity<ErrorResponse> NotCorrectAuthKeyException(AuthKeyNotMatchException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PhoneNumberNotFound.class)
    public ResponseEntity<ErrorResponse> PhoneNumberNotFound(PhoneNumberNotFound e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(LostNotFoundException.class)
    public ResponseEntity<ErrorResponse> LostNotFoundException(LostNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(FoundNotFoundException.class)
    public ResponseEntity<ErrorResponse> FoundNotFoundException(FoundNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> InvalidTokenException(InvalidTokenException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(DuplicateChattingRoomException.class)
    public ResponseEntity<ErrorResponse> DuplicateChattingRoomException(DuplicateChattingRoomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ChattingRoomNotFoundException.class)
    public ResponseEntity<ErrorResponse> ChattingRoomNotFoundException(ChattingRoomNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> ProcessValidationException(MethodArgumentNotValidException e) {
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
