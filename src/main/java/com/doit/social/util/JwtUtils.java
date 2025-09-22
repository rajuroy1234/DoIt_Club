package com.doit.social.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.doit.social.model.JwtKeyDocument;
import com.doit.social.repository.JwtKeyRepository;

import jakarta.annotation.PostConstruct;

@Component
public class JwtUtils {

	private final JwtKeyRepository keyRepository;
	private final Map<String, KeyPair> cache = new HashMap<>();

	public JwtUtils(JwtKeyRepository keyRepository) {
		this.keyRepository = keyRepository;
	}

	@PostConstruct
	public void init() {

		keyRepository.findAll().forEach(doc -> {
			if (doc.isActive()) {
				cache.put(doc.getId(), buildKeyPair(doc));
			}
		});
	}

	private KeyPair buildKeyPair(JwtKeyDocument doc) {
		try {
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PublicKey publicKey = kf
					.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(doc.getPublicKey())));
			PrivateKey privateKey = kf
					.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(doc.getPrivateKey())));
			return new KeyPair(publicKey, privateKey);
		} catch (Exception e) {
			throw new RuntimeException("Error building keypair for " + doc.getId(), e);
		}
	}

	@Cacheable(value = "jwtKeys", key = "#id")
	public KeyPair getKeyPair(String id) {
		return cache.computeIfAbsent(id, k -> keyRepository.findByIdAndActiveTrue(k).map(this::buildKeyPair)
				.orElseThrow(() -> new RuntimeException("Key not found: " + k)));
	}

	public KeyPair getLatestActiveKey() {
		return keyRepository.findFirstByActiveTrueOrderByCreatedAtDesc().map(this::buildKeyPair)
				.orElseThrow(() -> new RuntimeException("No active key found"));
	}
}
