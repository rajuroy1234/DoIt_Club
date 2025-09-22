package com.doit.social.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "invites")
public class Invite {

	@Id
	private String id;

	private String code;

	private boolean used = false;

	private String usedBy;

	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime expiresAt;

	public Invite() {
		super();
	}

	public Invite(String token, boolean used, LocalDateTime createdAt) {
        
		this.id = token;
        this.used = used;
        this.createdAt = createdAt;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(String usedBy) {
		this.usedBy = usedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
}
