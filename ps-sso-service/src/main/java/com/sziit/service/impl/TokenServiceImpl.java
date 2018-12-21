package com.sziit.service.impl;

import com.sziit.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private RedisTemplate<String ,String> redisTemplate;

	@Override
	public String getUserByToken(String token) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		return ops.get(token);
	}
}
