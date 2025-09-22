package com.doit.social.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doit.social.model.Invite;
import com.doit.social.repository.InviteRepository;

@Service
public class InviteService {

	@Autowired
	private InviteRepository inviteRepository;

	public Invite createInvite(int validDays) {

		Invite invite = new Invite();
		invite.setCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
		invite.setExpiresAt(LocalDateTime.now().plusDays(validDays));

		return inviteRepository.save(invite);
	}

	public boolean validateAndUseInvite(String code, String username) {
		
		Optional<Invite> optionalInvite = inviteRepository.findByCode(code.trim().toUpperCase());

		if (optionalInvite.isPresent()) {

			Invite invite = optionalInvite.get();

			if (invite.getExpiresAt() != null && invite.getExpiresAt().isBefore(LocalDateTime.now())) {
				return false;
			}

			if (StringUtils.isNotEmpty(invite.getUsedBy()) && !invite.getUsedBy().equals(username.toLowerCase())) {
				return false;
			}
			
			if (StringUtils.isEmpty(invite.getUsedBy())){
				
				invite.setUsedBy(username.toLowerCase());
			}

			invite.setUsed(true);			
			inviteRepository.save(invite);

			return true;
		}

		return false;
	}

	public boolean validateInvite(String token) {
		if (inviteRepository.existsByIdAndUsedTrue(token)) {
			return false; // already used token
		}
		// mark as used
		inviteRepository.save(new Invite(token, true, LocalDateTime.now()));
		return true;
	}

}
