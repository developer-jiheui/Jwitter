package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.dto.in.SignInDto;
import com.jwt.jwitter.web.dto.in.SignUpDto;
import com.jwt.jwitter.web.service.AuthService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth controller.
 */
@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public final class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        try {
            return ResponseEntity.ok(this.authService.signUp(signUpDto));
        } catch (final Exception exc) {
            return ResponseEntity.badRequest().body(
                Map.of("message", exc.getMessage())
            );
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        try {
            return ResponseEntity.ok(this.authService.signIn(signInDto));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }
}
