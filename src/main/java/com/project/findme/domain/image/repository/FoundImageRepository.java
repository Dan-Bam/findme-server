package com.project.findme.domain.image.repository;

import com.project.findme.domain.image.entity.FoundImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoundImageRepository extends JpaRepository<FoundImage, Long> {
}
