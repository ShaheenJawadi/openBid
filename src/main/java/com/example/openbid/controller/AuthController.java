package com.example.openbid.controller;


import com.example.openbid.dto.request.LoginRequest;
import com.example.openbid.dto.request.SignupRequest;
import com.example.openbid.dto.response.JwtResponse;
import com.example.openbid.model.User;
import com.example.openbid.repositories.UserRepository;
import com.example.openbid.services.RefreshTokenService;
import com.example.openbid.services.UserService;
import com.example.openbid.utilities.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Authenticate user credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Retrieve user from the database
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token with user roles
        String jwtToken = jwtUtils.generateToken(
                user.getUsername(),
                user.getRole().name()
        );

        // Generate refresh token
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();

        // Return response
        return new JwtResponse(
                jwtToken,
                refreshToken,
                user.getUsername(),
                user.getRole().name()
        );
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        try {
            User.Role userRole = User.Role.valueOf(signupRequest.getRole().toUpperCase());
            user.setRole(userRole);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role. Allowed roles: BUYER, SELLER, ADMIN");
        }

        userRepository.save(user);
        return "User registered successfully";
    }

}