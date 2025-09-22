package com.doit.social.controller;

import com.doit.social.model.Club;
import com.doit.social.service.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clubs")
public class ClubController {
	
	@Autowired
	private final ClubService service;

	public ClubController(ClubService service) {
		this.service = service;
	}

	@PostMapping
	public Club createClub(@RequestBody Club club) {
		return service.save(club);
	}

	@GetMapping("/category/{category}")
	public List<Club> getClubsByCategory(@PathVariable String category) {
		return service.findByCategory(category);
	}

	@PostMapping("/{clubId}/join/{userId}")
	public Optional<Club> joinClub(@PathVariable String clubId, @PathVariable String userId) {
		return service.joinClub(clubId, userId);
	}
}
