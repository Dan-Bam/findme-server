package com.project.findme.domain.lost.service.Impl;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.image.repository.LostImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.exception.LostNotFoundException;
import com.project.findme.domain.lost.facade.LostFacade;
import com.project.findme.domain.lost.presentation.dto.request.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.request.UpdateLostRequest;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.lost.repository.LostRepository;
import com.project.findme.domain.lost.service.LostService;
import com.project.findme.domain.lost.type.Category;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class LostServiceImpl implements LostService {

    private final UserUtil userUtil;
    private final S3Service s3Service;
    private final LostFacade lostFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLost(CreateLostRequest createLostRequest, List<MultipartFile> multipartFiles) {
        List<String> uploadFile = s3Service.upload(multipartFiles, "lost/" + createLostRequest.getCategory() + "/");
        Lost lost = lostFacade.saveLost(createLostRequest);

        uploadFile.forEach(file -> {
            lostFacade.saveLostImage(saveToUrl(lost, createLostRequest.getCategory().toString(), file));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LostImage saveToUrl(Lost lost, String category, String uploadFileUrl) {
        return LostImage.builder()
                .lost(lost)
                .imageUrl("https://findme-s3-bucket.s3.ap-northeast-2.amazonaws.com/lost/" + category + "/" + uploadFileUrl)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLost(Long lostId, UpdateLostRequest updateLostRequest, List<MultipartFile> multipartFileList) {
        Lost lost = lostFacade.findLostById(lostId);

        lostFacade.deleteLostImagesById(lostId);

        List<String> uploadFile = s3Service.upload(multipartFileList, "lost/" + updateLostRequest.getCategory() + "/");

        uploadFile.forEach(file -> {
            lostFacade.saveLostImage(saveToUrl(lost, updateLostRequest.getCategory().toString(), file));
        });

        lost.updateLost(updateLostRequest.getTitle(), updateLostRequest.getDescription(), updateLostRequest.getCategory(), updateLostRequest.getTags(), updateLostRequest.getPlace(), updateLostRequest.getLatitude(), updateLostRequest.getLongitude());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLost(Long lostId) {
        Lost lost = lostFacade.findLostById(lostId);

        lostFacade.deleteLostImagesById(lost.getId());

        lostFacade.deleteLostById(lostId);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public LostResponse findById(Long lostId) {
        return lostFacade.findLostDetailById(lostFacade.findLostById(lostId));
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findAll() {
        return lostFacade.findAllLost();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findByCategory(String category) {
        return lostFacade.findLostByCategory(category);
    }
}
