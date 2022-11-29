package com.project.findme.domain.image.domain.repository;

import com.project.findme.domain.image.domain.LostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LostImageRepository extends CrudRepository<LostImage, Long> {

    List<LostImage> findLostImageByLostId(Long lostId);
    void deleteByLostId(Long lostId);

}
