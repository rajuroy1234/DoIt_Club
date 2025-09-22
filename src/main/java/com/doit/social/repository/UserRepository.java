package com.doit.social.repository;

import com.doit.social.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByInterestsContaining(String interest);
    List<User> findByCity(String city);
}
