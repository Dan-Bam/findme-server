package com.project.findme.domain.found.service.Impl;

import com.project.findme.domain.found.domain.Found;
import com.project.findme.domain.found.facade.FoundFacade;
import com.project.findme.domain.found.presentation.dto.request.CreateFoundRequest;
import com.project.findme.domain.found.presentation.dto.request.UpdateFoundRequest;
import com.project.findme.domain.found.service.FoundService;
import com.project.findme.domain.image.domain.FoundImage;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.lost.type.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoundServiceImpl implements FoundService {

    private final FoundFacade foundFacade;
    private final S3Service s3Service;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFound(CreateFoundRequest createFoundRequest, List<MultipartFile> multipartFiles) {
        Found found = foundFacade.saveFound(createFoundRequest);

        uploadImageToS3(multipartFiles, found);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FoundImage saveToUrl(Found found, String category, String uploadFileUrl) {
        return FoundImage.builder()
                .found(found)
                .imageUrl("https://findme-s3-bucket.s3.ap-northeast-2.amazonaws.com/FOUND/" + Category.findName(category) + "/USER/" + found.getId() + "/" + uploadFileUrl)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFound(Long foundId, UpdateFoundRequest updateFoundRequest, List<MultipartFile> multipartFiles) {
        Found found = foundFacade.findFoundById(foundId);
        foundFacade.deleteFoundImageById(foundId);

        uploadImageToS3(multipartFiles, found);

        found.updateFound(updateFoundRequest.getTitle(), updateFoundRequest.getDescription(), updateFoundRequest.getTags(), updateFoundRequest.getIsSafe(), updateFoundRequest.getPlace(), updateFoundRequest.getLatitude(), updateFoundRequest.getLongitude());
    }

    private void uploadImageToS3(List<MultipartFile> multipartFiles, Found found) {
        List<String> uploadFile = s3Service.uploadFiles(
                multipartFiles, "FOUND/" + Category.findName(found.getCategory()) + "/USER/" + found.getId() + "/");

        uploadFile.forEach(file -> foundFacade.saveFoundImage(saveToUrl(found, found.getCategory(), file)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFound(Long foundId) {
        Found found = foundFacade.findFoundById(foundId);

        foundFacade.deleteFoundImageById(foundId);

        foundFacade.deleteFoundById(found);
    }


}
