package com.project.findme.domain.message.repository;

import com.project.findme.domain.message.entity.MessageAuth;
import org.springframework.data.repository.CrudRepository;

public interface MessageAuthRepository extends CrudRepository<MessageAuth, String> {
}
