package com.doit.social.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.doit.social.model.JwtKeyDocument;

public interface JwtKeyRepository extends MongoRepository<JwtKeyDocument, String> {
    
	Optional<JwtKeyDocument> findByIdAndActiveTrue(String id);
    Optional<JwtKeyDocument> findFirstByActiveTrueOrderByCreatedAtDesc();
}

