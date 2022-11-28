package com.project.findme.domain.user.service;

import com.project.findme.domain.found.presentation.dto.response.FoundResponse;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.findme.domain.user.presentation.dto.response.UserInfoResponse;

import java.util.List;

public interface UserService {

    List<LostResponse> findMyLost();
    List<FoundResponse> findMyFound();
    UserInfoResponse findMyInfo();
    void updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest);
    void logout();

}
