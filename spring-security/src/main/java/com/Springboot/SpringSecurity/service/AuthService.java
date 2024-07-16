package com.Springboot.SpringSecurity.service;

import com.Springboot.SpringSecurity.repository.UserRepository;
import com.Springboot.SpringSecurity.dto.ReqResDto;
import com.Springboot.SpringSecurity.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Handles user registration
    public ReqResDto signUp(ReqResDto registrationRequest) {
        ReqResDto reqResDto = new ReqResDto();
        try {
            // Check if the email is already registered
            if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
                reqResDto.setStatusCode(400);
                reqResDto.setMessage("Email is already in use");
                return reqResDto;
            }

            // Create a new user object and set its properties
            Users user = new Users();
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(registrationRequest.getRole());

            // Save the user to the database
            Users savedUser = userRepository.save(user);

            // Check if the user was saved successfully
            if (savedUser != null && savedUser.getId() != null) {
                reqResDto.setUsers(savedUser);
                reqResDto.setMessage("User has been registered successfully");
                reqResDto.setStatusCode(200);
            } else {
                reqResDto.setStatusCode(500);
                reqResDto.setMessage("Failed to register user");
            }

        } catch (Exception e) {
            reqResDto.setStatusCode(500);
            reqResDto.setError("Error during registration: " + e.getMessage());
        }
        return reqResDto;
    }

    // Handles user login
    public ReqResDto signIn(ReqResDto signInRequest) {
        ReqResDto reqResDto = new ReqResDto();
        try {
            String email = signInRequest.getEmail();
            String password = signInRequest.getPassword();

            // Check if the user exists in the database
            Optional<Users> userOptional = userRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                reqResDto.setStatusCode(401);
                reqResDto.setError("Invalid email");
                return reqResDto;
            }

            Users user = userOptional.get();

            // Check if the provided password matches the stored password
            if (!passwordEncoder.matches(password, user.getPassword())) {
                reqResDto.setStatusCode(401);
                reqResDto.setError("Invalid password");
                return reqResDto;
            }

            // Authenticate the user credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            // Generate JWT and refresh token
            String jwt = jwtUtils.generateToken(user);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            reqResDto.setStatusCode(200);
            reqResDto.setToken(jwt);
            reqResDto.setRefreshToken(refreshToken);
            reqResDto.setExpirationTime("24Hr");
            reqResDto.setMessage("User has been logged in successfully");

        } catch (Exception e) {
            reqResDto.setStatusCode(500);
            reqResDto.setError("Error during login: " + e.getMessage());
        }
        return reqResDto;
    }

    // Handles token refresh
    public ReqResDto refreshToken(ReqResDto refreshTokenRequest) {
        ReqResDto reqResDto = new ReqResDto();
        try {
            String email = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            Users user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

            // Validate the refresh token
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
                String newJwt = jwtUtils.generateToken(user);

                reqResDto.setStatusCode(200);
                reqResDto.setToken(newJwt);
                reqResDto.setRefreshToken(refreshTokenRequest.getToken());
                reqResDto.setExpirationTime("24Hr");
                reqResDto.setMessage("Token has been refreshed successfully");
            } else {
                reqResDto.setStatusCode(401);
                reqResDto.setMessage("Invalid refresh token");
            }
        } catch (Exception e) {
            reqResDto.setStatusCode(500);
            reqResDto.setError("Error during token refresh: " + e.getMessage());
        }
        return reqResDto;
    }
}
