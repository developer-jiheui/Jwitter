package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.dto.in.SignInDto;
import com.jwt.jwitter.web.dto.in.SignUpDto;
import com.jwt.jwitter.web.service.AuthService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public final class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public Map<String, String> test() {
        return Map.of("ds", "dsd");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto) {
        this.authService.signUp(signUpDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(this.authService.signIn(signInDto));
    }
}
