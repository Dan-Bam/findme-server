package com.project.findme.domain.lost.domain.repository;

import com.project.findme.domain.lost.domain.RecommendLost;
import com.project.findme.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecommendLostRepository extends CrudRepository<RecommendLost, Long> {
    List<RecommendLost> findRecommendLostByUser(User user);
}
