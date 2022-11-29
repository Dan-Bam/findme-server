package com.project.findme.domain.found.domain.repository;

import com.project.findme.domain.found.domain.Found;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoundRepository extends JpaRepository<Found, Long> {
    List<Found> findFoundByUserId(String id);
}
