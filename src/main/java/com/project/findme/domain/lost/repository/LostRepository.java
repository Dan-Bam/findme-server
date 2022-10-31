package com.project.findme.domain.lost.repository;

import com.project.findme.domain.lost.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostRepository extends JpaRepository<Lost, Long> {
}
