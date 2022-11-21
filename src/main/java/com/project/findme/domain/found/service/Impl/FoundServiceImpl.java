package com.project.findme.domain.found.service.Impl;

import com.project.findme.domain.found.entity.Found;
import com.project.findme.domain.found.exception.FoundNotFoundException;
import com.project.findme.domain.found.facade.FoundFacade;
import com.project.findme.domain.found.presentation.dto.CreateFoundRequest;
import com.project.findme.domain.found.presentation.dto.UpdateFoundRequest;
import com.project.findme.domain.found.repository.FoundRepository;
import com.project.findme.domain.found.service.FoundService;
import com.project.findme.domain.image.entity.FoundImage;
import com.project.findme.domain.image.repository.FoundImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.facade.UserFacade;
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
    private final UserFacade userFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFound(CreateFoundRequest createFoundRequest, List<MultipartFile> multipartFiles) {
        User user = userFacade.currentUser();
        List<String> uploadUrls = s3Service.upload(multipartFiles, "found/" + createFoundRequest.getCategory().toString() + "/");
        Found found = foundFacade.saveFound(createFoundRequest);

        uploadUrls.forEach(uploadUrl -> {
            foundFacade.saveFoundImage(saveToUrl(found, createFoundRequest.getCategory().toString(), uploadUrl));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FoundImage saveToUrl(Found found, String category, String uploadUrl) {
        return FoundImage.builder()
                .found(found)
                .imageUrl("https://findme-s3-bucket.s3.ap-northeast-2.amazonaws.com/found/" + category + "/" + uploadUrl)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFound(Long foundId, UpdateFoundRequest updateFoundRequest, List<MultipartFile> multipartFileList) {
        Found found = foundFacade.findFoundById(foundId);

        foundFacade.deleteFoundImageById(foundId);

        List<String> uploadFile = s3Service.upload(multipartFileList, "found/" + updateFoundRequest.getCategory() + "/");

        uploadFile.forEach(file -> {
            foundFacade.saveFoundImage(saveToUrl(found, updateFoundRequest.getCategory().toString(), file));
        });

        found.updateFound(updateFoundRequest.getTitle(), updateFoundRequest.getDescription(), updateFoundRequest.getCategory(), updateFoundRequest.getTags(), updateFoundRequest.getPlace(), updateFoundRequest.getLatitude(), updateFoundRequest.getLongitude());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFound(Long foundId) {
        Found found = foundFacade.findFoundById(foundId);

        foundFacade.deleteFoundImageById(foundId);

        foundFacade.deleteFoundById(found);
    }


}
