package com.doit.social.repository;

import com.doit.social.model.Club;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ClubRepository extends MongoRepository<Club, String> {
	List<Club> findByCategory(String category);

	List<Club> findByCity(String city);
}
