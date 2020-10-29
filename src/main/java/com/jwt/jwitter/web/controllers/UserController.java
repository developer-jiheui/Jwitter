package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.UserDto;
import com.jwt.jwitter.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Map;

/**
 * User Controller
 */
@RestController
@RequestMapping(value = "/api/auth")
public final class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            return ResponseEntity.ok(this.userService.getCurrentUser());
        } catch (final Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message","No user profile found"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> saveUserProfile(@RequestBody @Valid UserDto userDto) {
        User user = this.userService.getCurrentUser();

        user.setUsername(userDto.getUsername());
        user.setAvatar(userDto.getAvatar());
        user.setBio(userDto.getBio());
        user.setBirthday(userDto.getBirthday());
        user.setLocation(userDto.getLocation());
        user.setWebsite(userDto.getWebsite());

        try {
            return ResponseEntity.ok(this.userService.updateUser(user));
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message", "An error occurred saving your profile"), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
