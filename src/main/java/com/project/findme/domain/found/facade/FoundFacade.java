package com.project.findme.domain.found.facade;

import com.project.findme.domain.found.entity.Found;
import com.project.findme.domain.found.exception.FoundNotFoundException;
import com.project.findme.domain.found.presentation.dto.request.CreateFoundRequest;
import com.project.findme.domain.found.presentation.dto.response.FoundResponse;
import com.project.findme.domain.found.repository.FoundRepository;
import com.project.findme.domain.image.entity.FoundImage;
import com.project.findme.domain.image.repository.FoundImageRepository;
import com.project.findme.domain.image.service.S3Service;
import com.project.findme.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
            s3Service.deleteFile(file.getImageUrl().substring(58));
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

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<String> findFoundImageByFoundId(Long foundId) {
        return foundImageRepository.findFoundImageByFoundId(foundId).stream().map(FoundImage::getImageUrl).collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<FoundResponse> findMyFound() {
        return foundRepository.findFoundByUserId(userFacade.currentUser().getId())
                .stream().map(found -> FoundResponse.builder()
                        .id(found.getId())
                        .title(found.getTitle())
                        .description(found.getDescription())
                        .category(found.getCategory())
                        .imageUrls(findFoundImageByFoundId(found.getId()))
                        .tags(found.getTags())
                        .isSafe(found.isSafe())
                        .place(found.getPlace())
                        .latitude(found.getLatitude())
                        .longitude(found.getLongitude())
                        .build()).collect(Collectors.toList());
    }

}
