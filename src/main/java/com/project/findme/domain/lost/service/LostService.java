package com.project.findme.domain.lost.service;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.presentation.dto.CreateLostRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LostService {

    void createLost(CreateLostRequest createLostRequest, List<MultipartFile> multipartFileList);

    LostImage saveToUrl(Lost lost, String uploadFileUrl);
}
