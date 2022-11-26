package com.project.findme.domain.found.repository;

import com.project.findme.domain.found.entity.Found;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoundRepository extends CrudRepository<Found, Long> {
    List<Found> findFoundByUserId(String id);
}
