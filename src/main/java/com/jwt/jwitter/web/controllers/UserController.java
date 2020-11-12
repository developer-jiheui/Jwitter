package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.UserDto;
import com.jwt.jwitter.web.service.UserService;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 */
@RestController
@RequestMapping("/api/auth")
@Slf4j
public final class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            return ResponseEntity.ok(this.userService.getCurrentUser());
        } catch (final Exception e) {
            log.error("An error occured", e);
            return new ResponseEntity<>(Map.of("message", "No user profile found"), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update-user")
    public ResponseEntity<?> saveUserProfile(@RequestBody @Valid UserDto userDto) {
        User user = this.userService.getCurrentUser();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setBio(userDto.getBio());
        user.setBirthday(userDto.getBirthday());
        user.setLocation(userDto.getLocation());
        user.setWebsite(userDto.getWebsite());
        user.setAvatar(userDto.getAvatar());
        user.setCoverPhoto(userDto.getCoverPhoto());

        if(userDto.getUsername().contains(" ")){
            return new ResponseEntity<>(Map.of("message", "please don't user any empty space in the user name"), HttpStatus.FORBIDDEN);
        }
        if(userDto.getWebsite()!=null){
            Pattern p = Pattern.compile("(http(s)?://)?([\\w-]+\\.)+[\\w-]+[.com]+(/[/?%&=]*)?");
            Matcher m;
            m=p.matcher(userDto.getWebsite());
            boolean matches = m.matches();
            if(!matches){
                return new ResponseEntity<>(Map.of("message", "please enter proper website"), HttpStatus.FORBIDDEN);
            }
        }
        try {
            return ResponseEntity.ok(this.userService.updateUser(user));
        } catch (Exception e) {
            log.error("An error occured", e);
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-following/{user_id}")
    public ResponseEntity<?> getFollowing(@PathVariable("user_id") int user_id) {
        try {
            return ResponseEntity.ok(this.userService.getFollowing(user_id));
        } catch (final Exception e) {
            log.error("An error occured", e);
            return new ResponseEntity<>(Map.of("message", "No user profile found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-follower/{user_id}")
    public ResponseEntity<?> getFollower(@PathVariable("user_id") int user_id) {
        try {
            return ResponseEntity.ok(this.userService.getFollower(user_id));
        } catch (final Exception e) {
            log.error("An error occured", e);
            return new ResponseEntity<>(Map.of("message", "No user profile found"), HttpStatus.NOT_FOUND);
        }
    }
}
