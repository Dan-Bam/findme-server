package com.project.findme.domain.lost.service.Impl;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.image.repository.LostImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.exception.LostNotFoundException;
import com.project.findme.domain.lost.presentation.dto.request.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.lost.presentation.dto.request.UpdateLostRequest;
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
        List<String> uploadFile = s3Service.upload(multipartFiles, "lost/" + createLostRequest.getCategory() + "/");
        Lost lost = lostRepository.save(createLostRequest.toEntity(user));

        uploadFile.forEach(file -> {
            lostImageRepository.save(saveToUrl(lost, createLostRequest.getCategory().toString(), file));
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
        Lost lost = findLostById(lostId);

        lostImageRepository.findLostImageByLostId(lostId).forEach(file -> {
            s3Service.deleteFile(file.getImageUrl().substring(57));
            lostImageRepository.deleteByLostId(lost.getId());
        });

        List<String> uploadFile = s3Service.upload(multipartFileList, "lost/" + updateLostRequest.getCategory() + "/");

        uploadFile.forEach(file -> {
            lostImageRepository.save(saveToUrl(lost, updateLostRequest.getCategory().toString(), file));
        });

        lost.updateLost(updateLostRequest.getTitle(), updateLostRequest.getDescription(), updateLostRequest.getPlace(), updateLostRequest.getCategory(), updateLostRequest.getTags());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLost(Long lostId) {
        Lost lost = findLostById(lostId);
        List<LostImage> lostImage = lostImageRepository.findLostImageByLostId(lostId);

        lostImage.forEach(file -> {
            s3Service.deleteFile(file.getImageUrl().substring(57));
            lostImageRepository.deleteByLostId(lost.getId());
        });

        lostRepository.delete(lost);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public LostResponse findById(Long lostId) {
        Lost lost = findLostById(lostId);

        return LostResponse.builder()
                .id(lost.getId())
                .title(lost.getTitle())
                .description(lost.getDescription())
                .place(lost.getPlace())
                .category(lost.getCategory().getName())
                .lostImages(lostImageRepository.findLostImageByLostId(lostId))
                .tags(lost.getTags())
                .build();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findAll() {
        return lostRepository.findAll().stream().map(lost -> LostResponse.builder()
                .id(lost.getId())
                .title(lost.getTitle())
                .description(lost.getDescription())
                .place(lost.getPlace())
                .category(lost.getCategory().getName())
                .tags(lost.getTags())
                .lostImages(lostImageRepository.findLostImageByLostId(lost.getId()))
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findByCategory(String category) {
        return lostRepository.findLostByCategory(Category.findByName(category)).stream().map(lost -> LostResponse.builder()
                .id(lost.getId())
                .title(lost.getTitle())
                .description(lost.getDescription())
                .place(lost.getPlace())
                .category(lost.getCategory().getName())
                .tags(lost.getTags())
                .lostImages(lostImageRepository.findLostImageByLostId(lost.getId()))
                .build()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Lost findLostById(Long lostId) {
        return lostRepository.findById(lostId)
                .orElseThrow(() -> new LostNotFoundException("분실물을 찾을 수 없습니다."));
    }
}
