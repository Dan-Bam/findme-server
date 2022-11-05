package com.project.findme.domain.found.service.Impl;

import com.project.findme.domain.found.entity.Found;
import com.project.findme.domain.found.presentation.dto.CreateFoundRequest;
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
        List<String> uploadUrls = s3Service.upload(multipartFiles, "found/");
        Found found = foundRepository.save(createFoundRequest.toEntity(user));

        uploadUrls.forEach(uploadUrl -> {
            foundImageRepository.save(saveToUrl(found, uploadUrl));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FoundImage saveToUrl(Found found, String uploadUrl) {
        return FoundImage.builder()
                .found(found)
                .imageUrl("https://findme-s3-bucket.s3.ap-northeast-2.amazonaws.com/found/"+ uploadUrl)
                .build();
    }
}
