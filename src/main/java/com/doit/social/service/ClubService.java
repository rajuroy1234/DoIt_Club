package com.doit.social.service;

import com.doit.social.model.Club;
import com.doit.social.repository.ClubRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
	
	private final ClubRepository repo;

	public ClubService(ClubRepository repo) {
		this.repo = repo;
	}

	public Club save(Club club) {
		return repo.save(club);
	}

	public List<Club> findByCategory(String category) {
		return repo.findByCategory(category);
	}

	public Optional<Club> joinClub(String clubId, String userId) {
		Optional<Club> club = repo.findById(clubId);
		club.ifPresent(c -> {
			if (!c.getMembers().contains(userId)) {
				c.getMembers().add(userId);
				repo.save(c);
			}
		});
		return club;
	}
}
