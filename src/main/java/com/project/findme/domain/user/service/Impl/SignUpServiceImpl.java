package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.exception.DuplicateIdException;
import com.project.findme.domain.user.facade.UserFacade;
import com.project.findme.domain.user.presentation.dto.request.SignUpRequest;
import com.project.findme.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserFacade userFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest signUpRequest) {
        if(userFacade.existsById(signUpRequest.getId())) {
            throw new DuplicateIdException();
        }
        userFacade.saveUser(signUpRequest);
    }

}
