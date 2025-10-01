package com.budget.tracker.budgettracker.controllers;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class SessionTokenStore {
    private final Map<String, String> tokens = new ConcurrentHashMap<>();

    public String createSession(String username) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, username);
        return token;
    }

    public String getUser(String token) {
        return tokens.get(token);
    }

    public void removeSession(String token) {
        tokens.remove(token);
    }
}