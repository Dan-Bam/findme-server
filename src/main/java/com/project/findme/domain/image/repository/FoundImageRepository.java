package com.project.findme.domain.image.repository;

import com.project.findme.domain.found.entity.Found;
import com.project.findme.domain.image.entity.FoundImage;
import com.project.findme.domain.image.entity.LostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoundImageRepository extends JpaRepository<FoundImage, Long> {

    List<FoundImage> findFoundImageByFoundId(Long foundId);
    void deleteByFoundId(Long foundId);

}
