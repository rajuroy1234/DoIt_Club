package com.doit.social.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document("users")
public class User {
	@Id
	private String id;
	private String name;
	private String email;
	private String city;
	private List<Interests> interests;

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Interests> getInterests() {
		return interests;
	}

	public void setInterests(List<Interests> interests) {
		this.interests = interests;
	}
}
