package com.doit.social.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class InviteCache {

	private static final long INVITE_TTL_HOURS = 24; // TTL for cache

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public boolean contains(String code) {
		return redisTemplate.hasKey(code);
	}

	public void put(String code) {
		redisTemplate.opsForValue().set(code, "used", INVITE_TTL_HOURS, TimeUnit.HOURS);
	}

	public void remove(String code) {
		redisTemplate.delete(code);
	}
}
