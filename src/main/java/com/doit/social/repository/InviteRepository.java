package com.doit.social.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.doit.social.model.Invite;

public interface InviteRepository extends MongoRepository<Invite, String> {
	
	Optional<Invite> findByCode(String code);
	boolean existsByIdAndUsedTrue(String id);
}
