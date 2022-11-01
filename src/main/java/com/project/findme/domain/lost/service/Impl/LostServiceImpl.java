package com.project.findme.domain.lost.service.Impl;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.image.repository.LostImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.prentation.dto.CreateLostRequest;
import com.project.findme.domain.lost.repository.LostRepository;
import com.project.findme.domain.lost.service.LostService;
import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LostServiceImpl implements LostService {

    private final LostRepository lostRepository;
    private final UserUtil userUtil;
    private final S3Service s3Service;
    private final LostImageRepository lostImageRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLost(CreateLostRequest createLostRequest, List<MultipartFile> multipartFiles) {

        User user = userUtil.currentUser();
        List<String> uploadFile = s3Service.upload(multipartFiles, "/lost");
        Lost lost = lostRepository.save(createLostRequest.toEntity(user));

        uploadFile.forEach(file -> {
            lostImageRepository.save(saveToUrl(lost, file));
        });

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LostImage saveToUrl(Lost lost, String uploadFileUrl) {
        return LostImage.builder()
                .lost(lost)
                .imageUrl(uploadFileUrl)
                .build();
    }


}
