package com.doit.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doit.social.service.InviteService;

@Controller
public class AuthController {

	@Autowired
	private InviteService inviteService;
	
	@GetMapping(value = "/invite")
	public String validatePostPage(@RequestParam String inviteCode, Model model) {
		
		String username = "Hive";
		boolean valid = inviteService.validateAndUseInvite(inviteCode, username);

		if (valid) {
			model.addAttribute("message", "Welcome " + username + "! 🎉");
			return "home.html"; // home.html
		} else {
			model.addAttribute("errorMessage", "Invalid / Expired / Already used invite ❌");
			return "/child/error.html"; // error.html
		}
	}
}
