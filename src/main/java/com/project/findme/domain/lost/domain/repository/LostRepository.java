package com.project.findme.domain.lost.domain.repository;

import com.project.findme.domain.lost.domain.Lost;
import com.project.findme.domain.lost.type.Category;
import com.project.findme.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostRepository extends JpaRepository<Lost, Long> {
    List<Lost> findLostByUserId(String id);
    List<Lost> findLostByPlaceContaining(String place);
    List<Lost> findLostByCategoryAndPlaceContains(Category category, String place);
}
