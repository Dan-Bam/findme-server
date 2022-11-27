package com.project.findme.domain.lost.service.Impl;

import com.project.findme.domain.image.domain.LostImage;
import com.project.findme.infrastructure.s3.service.S3Service;
import com.project.findme.domain.lost.domain.Lost;
import com.project.findme.domain.lost.facade.LostFacade;
import com.project.findme.domain.lost.presentation.dto.request.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.request.UpdateLostRequest;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.lost.service.LostService;
import com.project.findme.domain.lost.type.Category;
import com.project.findme.infrastructure.feign.client.MakeImageFeign;
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
    private final MakeImageFeign makeImageFeign;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLost(CreateLostRequest createLostRequest, MultipartFile multipartFile) {

        log.info(createLostRequest.getCategory());
        log.info(Category.findName(createLostRequest.getCategory()));

        Lost lost = lostFacade.saveLost(createLostRequest);
        uploadImageToS3(multipartFile, lost);

//        makeImageFeign.makeImage(MakeImageRequest.builder()
//                        .lostId(lost.getId())
//                        .category(lost.getCategory())
//                        .tags(lost.getTags())
//                        .build());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LostImage saveToUrl(Lost lost, Category category, String uploadFileUrl) {
        return LostImage.builder()
                .lost(lost)
                .imageUrl("https://findme-s3-bucket.s3.ap-northeast-2.amazonaws.com/LOST/" + category + "/USER/" + lost.getId() + "/" + uploadFileUrl)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLost(Long lostId, UpdateLostRequest updateLostRequest, MultipartFile multipartFile) {
        Lost lost = lostFacade.findLostById(lostId);

        if(!multipartFile.isEmpty()) {
            lostFacade.deleteLostImagesById(lostId);
            uploadImageToS3(multipartFile, lost);
            lost.updateLost(updateLostRequest.getTitle(), updateLostRequest.getDescription(), updateLostRequest.getTags(), updateLostRequest.getIsSafe(), updateLostRequest.getPlace(), updateLostRequest.getLatitude(), updateLostRequest.getLongitude());
        }

        lost.updateLost(updateLostRequest.getTitle(), updateLostRequest.getDescription(), updateLostRequest.getTags(), updateLostRequest.getIsSafe(), updateLostRequest.getPlace(), updateLostRequest.getLatitude(), updateLostRequest.getLongitude());
    }

    private void uploadImageToS3(MultipartFile multipartFile, Lost lost) {
        String uploadFileUrl = s3Service.uploadFile(multipartFile, "LOST/" + lost.getCategory() + "/USER/" + lost.getId() + "/");

        lostFacade.saveLostImage(saveToUrl(lost, lost.getCategory(), uploadFileUrl));
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
