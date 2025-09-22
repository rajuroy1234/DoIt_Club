package com.doit.social.service;

import com.doit.social.model.User;
import com.doit.social.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public User save(User user) { return repo.save(user); }
    public List<User> findByInterest(String interest) { return repo.findByInterestsContaining(interest); }
    public List<User> findByCity(String city) { return repo.findByCity(city); }
}
