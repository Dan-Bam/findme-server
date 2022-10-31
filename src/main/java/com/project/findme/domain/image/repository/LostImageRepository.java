package com.project.findme.domain.image.repository;

import com.project.findme.domain.image.entity.LostImage;
import org.springframework.data.repository.CrudRepository;

public interface LostImageRepository extends CrudRepository<LostImage, Long> {
}
