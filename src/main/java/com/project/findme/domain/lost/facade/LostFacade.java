package com.project.findme.domain.lost.facade;

import com.project.findme.domain.image.domain.LostImage;
import com.project.findme.domain.image.domain.repository.LostImageRepository;
import com.project.findme.infrastructure.s3.service.S3Service;
import com.project.findme.domain.lost.domain.Lost;
import com.project.findme.domain.lost.exception.LostNotFoundException;
import com.project.findme.domain.lost.presentation.dto.request.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
import com.project.findme.domain.lost.domain.repository.LostRepository;
import com.project.findme.domain.lost.type.Category;
import com.project.findme.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class LostFacade {

    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final S3Service s3Service;
    private final UserFacade userFacade;

    @Transactional(rollbackFor = Exception.class)
    public Lost saveLost(CreateLostRequest createLostRequest) {
        return lostRepository.save(createLostRequest.toEntity(userFacade.currentUser()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveLostImage(LostImage lostImage) {
        lostImageRepository.save(lostImage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteLostImagesById(Long lostId) {
        lostImageRepository.findLostImageByLostId(lostId).forEach(file -> {
            s3Service.deleteFile(file.getImageUrl().substring(58));
            lostImageRepository.deleteByLostId(lostId);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteLostById(Lost lost) {
        lostRepository.delete(lost);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<String> findLostImageByLostId(Long lostId) {
        return lostImageRepository.findLostImageByLostId(lostId).stream().map(LostImage::getImageUrl).collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Lost findLostById(Long lostId) {
        return lostRepository.findById(lostId)
                .orElseThrow(LostNotFoundException::new);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<LostResponse> findAllLost() {
        return lostRepository.findAll()
                .stream()
                .map(lost -> LostResponse.of(lost, findLostImageByLostId(lost.getId()), isLostMine(lost)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public LostResponse findLostDetailById(Lost lost) {
        return LostResponse.of(lost, findLostImageByLostId(lost.getId()), isLostMine(lost));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findMyLost() {
        return lostRepository.findLostByUserId(userFacade.currentUser().getId())
                .stream()
                .map(lost -> LostResponse.of(lost, findLostImageByLostId(lost.getId()), isLostMine(lost)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<LostResponse> findByCategoryAndPlace(String category, String place) {
        if(category == null) {
            return lostRepository.findLostByPlaceContaining(place)
                    .stream()
                    .map(lost -> LostResponse.of(lost, findLostImageByLostId(lost.getId()), isLostMine(lost)))
                    .collect(Collectors.toList());
        }

        return lostRepository.findLostByCategoryAndPlaceContains(Category.findName(category), place)
                .stream()
                .map(lost -> LostResponse.of(lost, findLostImageByLostId(lost.getId()), isLostMine(lost)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public boolean isLostMine(Lost lost) {
        return lost.getUser().getId().equals(userFacade.currentUser().getId());
    }

}
