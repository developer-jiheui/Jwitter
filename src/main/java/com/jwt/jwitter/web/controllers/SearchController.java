package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.service.BookMarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jwt.jwitter.web.service.PostService;
import com.jwt.jwitter.web.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping(value ="api/auth",produces = "application/json")
public class SearchController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

//    @GetMapping("/search")
//    public ResponseEntity<List<User>> searchUsers(@RequestParam(name = "name") final String name) {
//        final List<User> users = this.userService.searchUsers(name);
//        if (users.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.ok(users);
//        }
//    }


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
