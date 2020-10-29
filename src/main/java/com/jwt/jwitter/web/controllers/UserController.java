package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.Map;

/**
 * User Controller
 */
@RestController
@RequestMapping(value = "/api/auth")
public final class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            return ResponseEntity.ok(this.userService.getCurrentUser());
        } catch (final Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message","No user profile found"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/profile/")
    public ResponseEntity<?> saveUserProfile() {
        User user = this.userService.getCurrentUser();

        try {
            return ResponseEntity.ok(this.userService.saveUser(user));
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message", "An error occurred saving your profile"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
