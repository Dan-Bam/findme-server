package com.project.findme.domain.lost.repository;

import com.project.findme.domain.lost.entity.Lost;
import com.project.findme.domain.lost.type.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostRepository extends JpaRepository<Lost, Long> {
    List<Lost> findLostByCategory(Category category);
}
