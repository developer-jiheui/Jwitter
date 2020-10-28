package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.dto.in.PostDto;
import com.jwt.jwitter.web.service.PostService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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

    @PostMapping("/tweet")
    public ResponseEntity<?> tweet(@RequestBody PostDto postDto) {
        try {
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
            return ResponseEntity.ok(this.postService.get(user_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }
}
