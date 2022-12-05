package com.project.findme.domain.found.service.Impl;

import com.project.findme.domain.found.domain.Found;
import com.project.findme.domain.found.facade.FoundFacade;
import com.project.findme.domain.found.presentation.dto.request.CreateFoundRequest;
import com.project.findme.domain.found.presentation.dto.request.UpdateFoundRequest;
import com.project.findme.domain.found.presentation.dto.response.FoundResponse;
import com.project.findme.domain.found.service.FoundService;
import com.project.findme.domain.image.domain.FoundImage;
import com.project.findme.domain.lost.type.Category;
import com.project.findme.infrastructure.feign.client.ImageFeignClient;
import com.project.findme.infrastructure.feign.dto.request.CheckImageRequest;
import com.project.findme.infrastructure.feign.dto.response.CheckImageResponse;
import com.project.findme.infrastructure.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FoundServiceImpl implements FoundService {

    private final FoundFacade foundFacade;
    private final S3Service s3Service;
    private final ImageFeignClient imageFeignClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFound(CreateFoundRequest createFoundRequest, MultipartFile multipartFile) {
        Found found = foundFacade.saveFound(createFoundRequest);
        uploadImageToS3(multipartFile, found);

//        CheckImageResponse checkImage = imageFeignClient.checkImage(CheckImageRequest.builder()
//                .userImage(foundFacade.findFoundImageByFoundId(found.getId()).get(0))
//                .category(found.getCategory())
//                .foundId(found.getId())
//                .build());
//
//        checkImage.getLostId().forEach(lostId -> foundFacade.saveRecommendLost(Long.valueOf(lostId), found));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FoundImage saveToUrl(Found found, Category category, String uploadFileUrl) {
        return FoundImage.builder()
                .found(found)
                .imageUrl("https://findme-s3-bucket.s3.ap-northeast-2.amazonaws.com/FOUND/" + found.getCategory() + "/USER/" + found.getId() + "/" + uploadFileUrl)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFound(Long foundId, UpdateFoundRequest updateFoundRequest, MultipartFile multipartFile) {
        Found found = foundFacade.findFoundById(foundId);

        if(multipartFile != null) {
            foundFacade.deleteFoundImageById(foundId);
            uploadImageToS3(multipartFile, found);
            found.updateFound(updateFoundRequest.getTitle(), updateFoundRequest.getDescription(), updateFoundRequest.getTags(), updateFoundRequest.getPlace(), updateFoundRequest.getLatitude(), updateFoundRequest.getLongitude());
        }

        found.updateFound(updateFoundRequest.getTitle(), updateFoundRequest.getDescription(), updateFoundRequest.getTags(), updateFoundRequest.getPlace(), updateFoundRequest.getLatitude(), updateFoundRequest.getLongitude());
    }

    private void uploadImageToS3(MultipartFile multipartFile, Found found) {
        String uploadFileUrl = s3Service.uploadFile(multipartFile, "FOUND/" + found.getCategory() + "/USER/" + found.getId() + "/");

        foundFacade.saveFoundImage(saveToUrl(found, found.getCategory(), uploadFileUrl));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFound(Long foundId) {
        Found found = foundFacade.findFoundById(foundId);

        foundFacade.deleteFoundImageById(foundId);

        foundFacade.deleteFoundById(found);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public FoundResponse findById(Long foundId) {
        return foundFacade.findById(foundFacade.findFoundById(foundId));
    }

}
