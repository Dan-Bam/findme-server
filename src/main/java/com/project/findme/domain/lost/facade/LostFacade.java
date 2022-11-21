package com.project.findme.domain.lost.facade;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.image.repository.LostImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.exception.LostNotFoundException;
import com.project.findme.domain.lost.presentation.dto.request.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.lost.repository.LostRepository;
import com.project.findme.domain.lost.type.Category;
import com.project.findme.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LostFacade {

    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final S3Service s3Service;
    private final UserFacade userFacade;

    @Transactional(rollbackFor = Exception.class)
    public Lost saveLost(CreateLostRequest createLostRequest) {
        return createLostRequest.toEntity(userFacade.currentUser());
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveLostImage(LostImage lostImage) {
        lostImageRepository.save(lostImage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteLostImagesById(Long lostId) {
        lostImageRepository.findLostImageByLostId(lostId).forEach(file -> {
            s3Service.deleteFile(file.getImageUrl().substring(57));
            lostImageRepository.deleteByLostId(lostId);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteLostById(Long lostId) {
        lostRepository.delete(findLostById(lostId));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findAllLost() {
        return lostRepository.findAll().stream().map(lost -> LostResponse.builder()
                .id(lost.getId())
                .title(lost.getTitle())
                .description(lost.getDescription())
                .category(lost.getCategory().getName())
                .tags(lost.getTags())
                .lostImages(findLostImageByLost(lost.getId()))
                .place(lost.getPlace())
                .latitude(lost.getLatitude())
                .longitude(lost.getLongitude())
                .build()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findLostByCategory(String category) {
        return lostRepository.findLostByCategory(Category.findByName(category)).stream().map(lost -> LostResponse.builder()
                .id(lost.getId())
                .title(lost.getTitle())
                .description(lost.getDescription())
                .category(lost.getCategory().getName())
                .tags(lost.getTags())
                .lostImages(findLostImageByLost(lost.getId()))
                .place(lost.getPlace())
                .latitude(lost.getLatitude())
                .longitude(lost.getLongitude())
                .build()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public LostResponse findLostDetailById(Lost lost) {
        return LostResponse.builder()
                .id(lost.getId())
                .title(lost.getTitle())
                .description(lost.getDescription())
                .category(lost.getCategory().getName())
                .lostImages(findLostImageByLost(lost.getId()))
                .tags(lost.getTags())
                .place(lost.getPlace())
                .latitude(lost.getLatitude())
                .longitude(lost.getLongitude())
                .build();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<String> findLostImageByLost(Long lostId) {
        return lostImageRepository.findLostImageByLostId(lostId).stream().map(LostImage::getImageUrl).collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Lost findLostById(Long lostId) {
        return lostRepository.findById(lostId)
                .orElseThrow(() -> new LostNotFoundException("분실물을 찾을 수 없습니다."));
    }
}
