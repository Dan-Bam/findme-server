package com.project.findme.domain.user.repository;

import com.project.findme.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(String id);
    boolean existsById(String id);

}
