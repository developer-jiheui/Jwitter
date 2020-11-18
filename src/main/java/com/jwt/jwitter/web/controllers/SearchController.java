package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.models.Comment;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.service.PostService;
import com.jwt.jwitter.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class SearchController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/search/{searchWord}")
    public ResponseEntity<?> searchUsers(@PathVariable(name = "searchWord") final String name) {
        final List<User> users = this.userService.searchUsers(name);
        try {
            return ResponseEntity.ok(users);
        } catch (final Exception e) {
            log.error("An error occured", e);
            return new ResponseEntity<>(Map.of("message", "No user profile found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tweet-search/{searchWord}")
    public ResponseEntity<?> searchTweets(@PathVariable(name = "searchWord") final String name) {
        final List<Comment> tweets = this.postService.searchTweets(name);
        try {
            return ResponseEntity.ok(tweets);
        } catch (final Exception e) {
            log.error("An error occured", e);
            return new ResponseEntity<>(Map.of("message", "No user profile found"), HttpStatus.NOT_FOUND);
        }

    }
//    @PostMapping("/{postId}")
//    public ResponseEntity<?> createBookMark(
//            final Principal user,
//            @PathVariable("postId") final int postId) {
//        this.searchService.create(user.getName(), postId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<List<?>> bookMarksByUser(final Principal user) {
//        return ResponseEntity.ok(this.searchService.getBookMarks(user.getName()));
//    }
}
