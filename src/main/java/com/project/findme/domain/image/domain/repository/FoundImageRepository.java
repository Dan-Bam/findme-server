package com.project.findme.domain.image.domain.repository;

import com.project.findme.domain.image.domain.FoundImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoundImageRepository extends JpaRepository<FoundImage, Long> {

    List<FoundImage> findFoundImageByFoundId(Long foundId);
    void deleteByFoundId(Long foundId);

}