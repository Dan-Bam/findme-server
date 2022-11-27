package com.project.findme.domain.found.service;

import com.project.findme.domain.found.domain.Found;
import com.project.findme.domain.found.presentation.dto.request.CreateFoundRequest;
import com.project.findme.domain.found.presentation.dto.request.UpdateFoundRequest;
import com.project.findme.domain.image.domain.FoundImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoundService {

    void createFound(CreateFoundRequest createFoundRequest, MultipartFile multipartFile);
    FoundImage saveToUrl(Found found, String category, String uploadFileUrl);
    void updateFound(Long foundId, UpdateFoundRequest updateFoundRequest, MultipartFile multipartFile);
    void deleteFound(Long foundId);

}
