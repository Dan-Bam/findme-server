package com.project.findme.domain.lost.repository;

import com.project.findme.domain.lost.domain.Lost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostRepository extends JpaRepository<Lost, Long> {
    List<Lost> findLostByUserId(String id);
    List<Lost> findLostByCategoryAndPlaceContains(String category, String place);
}
