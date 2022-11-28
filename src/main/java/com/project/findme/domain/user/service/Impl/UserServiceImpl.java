package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.found.facade.FoundFacade;
import com.project.findme.domain.found.presentation.dto.response.FoundResponse;
import com.project.findme.domain.lost.facade.LostFacade;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.facade.UserFacade;
import com.project.findme.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.findme.domain.user.presentation.dto.response.UserInfoResponse;
import com.project.findme.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final LostFacade lostFacade;
    private final FoundFacade foundFacade;
    private final UserFacade userFacade;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findMyLost() {
        return lostFacade.findMyLost();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<FoundResponse> findMyFound() {
        return foundFacade.findMyFound();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserInfoResponse findMyInfo() {
        User user = userFacade.currentUser();
        return UserInfoResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest) {
        User user = userFacade.currentUser();

        user.updateUserInfo(updateUserInfoRequest.getUserName(), updateUserInfoRequest.getAddress(), updateUserInfoRequest.getPhoneNumber());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout() {
        User user = userFacade.currentUser();
        user.updateRefreshToken(null);
    }

}
