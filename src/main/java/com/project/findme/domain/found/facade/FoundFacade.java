package com.project.findme.domain.found.facade;

import com.project.findme.domain.found.entity.Found;
import com.project.findme.domain.found.exception.FoundNotFoundException;
import com.project.findme.domain.found.presentation.dto.CreateFoundRequest;
import com.project.findme.domain.found.repository.FoundRepository;
import com.project.findme.domain.image.entity.FoundImage;
import com.project.findme.domain.image.repository.FoundImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class FoundFacade {

    private final FoundRepository foundRepository;
    private final FoundImageRepository foundImageRepository;
    private final S3Service s3Service;
    private final UserFacade userFacade;

    @Transactional(rollbackFor = Exception.class)
    public Found saveFound(CreateFoundRequest createFoundRequest) {

        return foundRepository.save(createFoundRequest.toEntity(userFacade.currentUser()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveFoundImage(FoundImage foundImage) {
        foundImageRepository.save(foundImage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteFoundImageById(Long foundImageId) {
        foundImageRepository.findFoundImageByFoundId(foundImageId).forEach(file -> {
            s3Service.deleteFile(file.getImageUrl().substring(57));
            foundImageRepository.deleteByFoundId(foundImageId);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteFoundById(Found found) {
        foundRepository.delete(found);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Found findFoundById(Long foundId) {
        return foundRepository.findById(foundId).orElseThrow(FoundNotFoundException::new);
    }

}
