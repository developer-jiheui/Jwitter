package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.PostDto;
import com.jwt.jwitter.web.service.AuthService;
import com.jwt.jwitter.web.service.PostService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth controller.
 */
@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public final class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private AuthService authService;

    @PostMapping("/tweet")
    public ResponseEntity<?> tweet(@RequestBody PostDto postDto) {
        try {
            final UserDetails auth= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final String email=auth.getUsername();
            User u =authService.getUserByEmail(email);
            postDto.setUser_id(u.getId());
            return ResponseEntity.ok(this.postService.tweet(postDto));
        } catch (final Exception exc) {
            return ResponseEntity.badRequest().body(
                Map.of("message", exc.getMessage())
            );
        }
    }

    @GetMapping("/tweets/{user_id}")
    public ResponseEntity<?> getTweets(@PathVariable("user_id") int user_id) {
        try {
            return ResponseEntity.ok(this.postService.getPostsByUser(user_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }
}
