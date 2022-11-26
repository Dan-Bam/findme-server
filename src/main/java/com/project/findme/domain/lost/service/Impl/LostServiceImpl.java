package com.project.findme.domain.lost.service.Impl;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.facade.LostFacade;
import com.project.findme.domain.lost.presentation.dto.request.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.request.UpdateLostRequest;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.lost.service.LostService;
import com.project.findme.domain.lost.type.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class LostServiceImpl implements LostService {

    private final S3Service s3Service;
    private final LostFacade lostFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLost(CreateLostRequest createLostRequest, List<MultipartFile> multipartFiles) {
        Lost lost = lostFacade.saveLost(createLostRequest);
        uploadImageToS3(multipartFiles, lost);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LostImage saveToUrl(Lost lost, String category, String uploadFileUrl) {
        return LostImage.builder()
                .lost(lost)
                .imageUrl("https://findme-s3-bucket.s3.ap-northeast-2.amazonaws.com/LOST/" + Category.findName(category) + "/USER/" + lost.getId() + "/" + uploadFileUrl)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLost(Long lostId, UpdateLostRequest updateLostRequest, List<MultipartFile> multipartFiles) {
        Lost lost = lostFacade.findLostById(lostId);
        lostFacade.deleteLostImagesById(lostId);

        uploadImageToS3(multipartFiles, lost);
        lost.updateLost(updateLostRequest.getTitle(), updateLostRequest.getDescription(), updateLostRequest.getTags(), updateLostRequest.getIsSafe(), updateLostRequest.getPlace(), updateLostRequest.getLatitude(), updateLostRequest.getLongitude());
    }

    private void uploadImageToS3(List<MultipartFile> multipartFiles, Lost lost) {
        List<String> uploadFile = s3Service.uploadFiles(
                multipartFiles, "LOST/" + Category.findName(lost.getCategory()) + "/USER/" + lost.getId() + "/");

        uploadFile.forEach(file -> lostFacade.saveLostImage(saveToUrl(lost, lost.getCategory(), file)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLost(Long lostId) {
        Lost lost = lostFacade.findLostById(lostId);
        lostFacade.deleteLostImagesById(lost.getId());
        lostFacade.deleteLostById(lost);
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
    public List<LostResponse> findByCategoryAndPlace(String category, String place) {
        return lostFacade.findByCategoryAndPlace(category, place);
    }

}
