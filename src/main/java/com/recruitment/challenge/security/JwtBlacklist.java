package com.recruitment.challenge.security;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class JwtBlacklist {
	
	private static Map<String, Date> jwtBlacklist = new ConcurrentHashMap<>();

	public synchronized void add(String token) {
		jwtBlacklist.putIfAbsent(token, new Date());
	}

	public boolean contains(String token) {
		return jwtBlacklist.containsKey(token);
	}

	public synchronized void cleanBlacklist() {
		jwtBlacklist = jwtBlacklist.entrySet().stream()
				.filter(entry -> entry.getValue().before(new Date()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}

}