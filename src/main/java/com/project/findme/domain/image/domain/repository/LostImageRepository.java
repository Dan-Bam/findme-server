package com.project.findme.domain.image.domain.repository;

import com.project.findme.domain.image.domain.LostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostImageRepository extends JpaRepository<LostImage, Long> {

    List<LostImage> findLostImageByLostId(Long lostId);
    void deleteByLostId(Long lostId);

}
