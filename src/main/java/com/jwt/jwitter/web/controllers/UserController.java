package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.UserDto;
import com.jwt.jwitter.web.service.AuthService;
import com.jwt.jwitter.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User Controller
 */
@RestController
@RequestMapping("/api/auth")
@Slf4j
public final class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping("/get-profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            return ResponseEntity.ok(this.userService.getCurrentUser());
        } catch (final Exception e) {
            log.error("An error occured", e);
            return new ResponseEntity<>(Map.of("message", "No user profile found"), HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/users")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(name = "name") final String name) {
        final List<User> users = this.userService.searchUsers(name);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
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
            return new ResponseEntity<>(Map.of("message", "please don't use any empty space in the user name"), HttpStatus.FORBIDDEN);
        }
        if(userDto.getWebsite()!=null&&userDto.getWebsite().length()>0){
            Pattern p = Pattern.compile("(http(s)?://)?([\\w-]+\\.)+[\\w-]+[.com|.org|.ca|.net|.info]+(/[/?%&=]*)?");
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

    @PutMapping("/add-following/{user_id}")
    public ResponseEntity<?> addFollowing(@PathVariable("user_id") int user_id) {
        User user = this.userService.getCurrentUser();
        int currUserid = user.getId();
        try {
            return ResponseEntity.ok(this.userService.addFollowing(currUserid, user_id));
        } catch (final Exception e) {
            log.error("An error occured", e);
            return new ResponseEntity<>(Map.of("message", "No user profile found"), HttpStatus.NOT_FOUND);
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

    @GetMapping("/toggleFollow/{follow_user_id}/{toggle}")
    public ResponseEntity<?> toggleFollow(@PathVariable("follow_user_id") int user_id, @PathVariable("toggle") boolean toggle) {
        try {
            final UserDetails auth = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final String email = auth.getUsername();
            User u = authService.getUserByEmail(email);
            int users_id = u.getId();
            return ResponseEntity.ok(this.userService.getFollower(user_id));

//            return ResponseEntity.ok(this.userService.toggleFollow(user_id, follow_user_id, toggle));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }
}
