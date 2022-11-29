package com.project.findme.domain.user.presentation;

import com.project.findme.domain.found.presentation.dto.response.FoundResponse;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.findme.domain.user.presentation.dto.response.UserInfoResponse;
import com.project.findme.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/lost")
    public ResponseEntity<List<LostResponse>> findMyLost() {
        return new ResponseEntity<>(userService.findMyLost(), HttpStatus.OK);
    }

    @GetMapping("/found")
    public ResponseEntity<List<FoundResponse>> findMyFound() {
        return new ResponseEntity<>(userService.findMyFound(), HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<FoundResponse>> findMyRecommendLost() {
        return new ResponseEntity<>(userService.findRecommendLost(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<UserInfoResponse> findMyInfo() {
        return new ResponseEntity<>(userService.findMyInfo(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Void> updateUserInfo(@RequestBody UpdateUserInfoRequest updateUserInfoRequest) {
        userService.updateUserInfo(updateUserInfoRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout() {
        userService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
