package com.project.findme.domain.found.service.Impl;

import com.project.findme.domain.found.entity.Found;
import com.project.findme.domain.found.exception.FoundNotFoundException;
import com.project.findme.domain.found.presentation.dto.CreateFoundRequest;
import com.project.findme.domain.found.presentation.dto.UpdateFoundRequest;
import com.project.findme.domain.found.repository.FoundRepository;
import com.project.findme.domain.found.service.FoundService;
import com.project.findme.domain.image.entity.FoundImage;
import com.project.findme.domain.image.repository.FoundImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoundServiceImpl implements FoundService {

    private final FoundRepository foundRepository;
    private final FoundImageRepository foundImageRepository;
    private final UserUtil userUtil;
    private final S3Service s3Service;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFound(CreateFoundRequest createFoundRequest, List<MultipartFile> multipartFiles) {
        User user = userUtil.currentUser();
        List<String> uploadUrls = s3Service.upload(multipartFiles, "found/" + createFoundRequest.getCategory().toString() + "/");
        Found found = foundRepository.save(createFoundRequest.toEntity(user));

        uploadUrls.forEach(uploadUrl -> {
            foundImageRepository.save(saveToUrl(found, createFoundRequest.getCategory().toString(), uploadUrl));
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
        Found found = findFoundById(foundId);

        foundImageRepository.findFoundImageByFoundId(found.getId()).forEach(file -> {
            s3Service.deleteFile(file.getImageUrl().substring(57));
            foundImageRepository.deleteByFoundId(found.getId());
        });

        List<String> uploadFile = s3Service.upload(multipartFileList, "found/" + updateFoundRequest.getCategory() + "/");

        uploadFile.forEach(file -> {
            foundImageRepository.save(saveToUrl(found, updateFoundRequest.getCategory().toString(), file));
        });

        found.updateFound(updateFoundRequest.getTitle(), updateFoundRequest.getDescription(), updateFoundRequest.getCategory(), updateFoundRequest.getTags(), updateFoundRequest.getPlace(), updateFoundRequest.getLatitude(), updateFoundRequest.getLongitude());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFound(Long foundId) {
        Found found = findFoundById(foundId);
        List<FoundImage> foundImages = foundImageRepository.findFoundImageByFoundId(foundId);

        foundImages.forEach(file -> {
            s3Service.deleteFile(file.getImageUrl().substring(57));
            foundImageRepository.deleteByFoundId(found.getId());
        });

        foundRepository.delete(found);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Found findFoundById(Long findId) {
        return foundRepository.findById(findId).orElseThrow(() -> new FoundNotFoundException("습득물 게시글을 찾을 수 없습니다."));
    }
}
