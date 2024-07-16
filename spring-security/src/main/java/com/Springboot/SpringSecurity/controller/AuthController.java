package com.Springboot.SpringSecurity.controller;

import com.Springboot.SpringSecurity.dto.ReqResDto;
import com.Springboot.SpringSecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqResDto> signUp(@RequestBody ReqResDto signUpRequest) {
        return ResponseEntity.ok(authService.signUp(signUpRequest));           // send request to AuthService and return the response
    }

    @PostMapping("/signin")
    public ResponseEntity<ReqResDto> signIn(@RequestBody ReqResDto signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqResDto> refreshToken(@RequestBody ReqResDto refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
