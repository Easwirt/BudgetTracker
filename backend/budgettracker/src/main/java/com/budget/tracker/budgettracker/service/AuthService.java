package com.budget.tracker.budgettracker.service;

import com.budget.tracker.budgettracker.dto.SignUpRequest;
import com.budget.tracker.budgettracker.controllers.SessionTokenStore;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SessionTokenStore tokenStore;

    public AuthService(JdbcUserDetailsManager userDetailsManager,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       SessionTokenStore tokenStore) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
    }

    public void signup(SignUpRequest signUpRequest) {
        String username = signUpRequest.getUsername();
        String password = signUpRequest.getPassword();

        if (username == null || username.isBlank() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be empty");
        }

        if (userDetailsManager.userExists(username)) {
            throw new IllegalStateException("Username is already taken");
        }

        String encodedPassword = passwordEncoder.encode(password);
        UserDetails user = User.withUsername(username)
                .password(encodedPassword)
                .roles("USER")
                .build();

        userDetailsManager.createUser(user);
    }

    public String login(SignUpRequest request) throws AuthenticationException {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        if (auth != null && auth.isAuthenticated()) {
            return tokenStore.createSession(request.getUsername());
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
