package com.project.findme.domain.found.domain.repository;

import com.project.findme.domain.found.domain.Found;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoundRepository extends CrudRepository<Found, Long> {
    List<Found> findFoundByUserId(String id);
}
