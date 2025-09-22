package com.doit.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doit.social.model.Invite;
import com.doit.social.service.InviteService;

@RestController
@RequestMapping("/invite")
public class InviteController {

	@Autowired
	private InviteService inviteService;

	@PostMapping("/create")
	public ResponseEntity<Invite> create(@RequestParam(defaultValue = "7") int days) {
		
		return ResponseEntity.ok(inviteService.createInvite(days));
	}

	@PostMapping("/validate")
	public ResponseEntity<String> validate(@RequestParam String code) {
		
		String username = "Hive";
		boolean valid = inviteService.validateAndUseInvite(code, username);
		if (valid) {
			return ResponseEntity.ok("Invite accepted!");
		} else {
			return ResponseEntity.badRequest().body("Invalid / Expired / Already used invite");
		}
	}	 
}
