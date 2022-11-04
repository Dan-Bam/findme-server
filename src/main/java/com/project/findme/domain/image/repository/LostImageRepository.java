package com.project.findme.domain.image.repository;

import com.project.findme.domain.image.entity.LostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LostImageRepository extends JpaRepository<LostImage, Long> {

    List<LostImage> findLostImageByLost_LostId(Long lostId);

}
