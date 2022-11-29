package com.project.findme.domain.image.domain.repository;

import com.project.findme.domain.image.domain.FoundImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoundImageRepository extends CrudRepository<FoundImage, Long> {

    List<FoundImage> findFoundImageByFoundId(Long foundId);
    void deleteByFoundId(Long foundId);

}
