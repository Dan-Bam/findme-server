package com.project.findme.domain.lost.service.Impl;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.image.repository.LostImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.exception.LostNotFoundException;
import com.project.findme.domain.lost.presentation.dto.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.LostResponseDto;
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

    private final LostRepository lostRepository;
    private final UserUtil userUtil;
    private final S3Service s3Service;
    private final LostImageRepository lostImageRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createLost(CreateLostRequest createLostRequest, List<MultipartFile> multipartFiles) {

        User user = userUtil.currentUser();
        List<String> uploadFile = s3Service.upload(multipartFiles, "lost/");
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
                .imageUrl("https://findme-s3-bucket.s3.ap-northeast-2.amazonaws.com/lost/" + uploadFileUrl)
                .build();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public LostResponseDto findById(Long lostId) {
        Lost lost = lostRepository.findById(lostId)
                .orElseThrow(() -> new LostNotFoundException("분실물을 찾을 수 없습니다."));

        List<LostImage> imageByLostId = lostImageRepository.findLostImageByLost_LostId(lostId);

        return LostResponseDto.builder()
                .title(lost.getTitle())
                .description(lost.getDescription())
                .place(lost.getPlace())
                .category(lost.getCategory())
                .lostImages(imageByLostId)
                .tags(lost.getTags())
                .safeTransaction(lost.isSafeTransaction())
                .build();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponseDto> findAll() {
        return lostRepository.findAll().stream().map(lost -> LostResponseDto.builder()
                .title(lost.getTitle())
                .description(lost.getDescription())
                .place(lost.getPlace())
                .category(lost.getCategory())
                .tags(lost.getTags())
                .lostImages(lostImageRepository.findLostImageByLost_LostId(lost.getLostId()))
                .safeTransaction(lost.isSafeTransaction())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponseDto> findByCategory(String category) {
        return lostRepository.findLostByCategory(Category.findByName(category)).stream().map(lost -> LostResponseDto.builder()
                .id(lost.getLostId())
                .title(lost.getTitle())
                .description(lost.getDescription())
                .place(lost.getPlace())
                .category(lost.getCategory())
                .tags(lost.getTags())
                .lostImages(lostImageRepository.findLostImageByLost_LostId(lost.getLostId()))
                .safeTransaction(lost.isSafeTransaction())
                .build()).collect(Collectors.toList());
    }


}
