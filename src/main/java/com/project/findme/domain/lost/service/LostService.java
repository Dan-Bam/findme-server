package com.project.findme.domain.lost.service;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.presentation.dto.request.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.lost.presentation.dto.request.UpdateLostRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LostService {

    void createLost(CreateLostRequest createLostRequest, List<MultipartFile> multipartFileList);
    LostImage saveToUrl(Lost lost,String category, String uploadFileUrl);
    void updateLost(Long lostId, UpdateLostRequest updateLostRequest, List<MultipartFile> multipartFileList);
    void deleteLost(Long lostId);
    LostResponse findById(Long lostId);
    List<LostResponse> findAll();
    List<LostResponse> findByCategory(String category);

}
