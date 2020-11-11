package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.service.BookMarksService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookmarks")
public class BookMarksController {

    @Autowired
    private BookMarksService bookMarksService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> createBookMark(
        final Principal user,
        @PathVariable("postId") final int postId) {
        this.bookMarksService.create(user.getName(), postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<?>> bookMarksByUser(final Principal user) {
        return ResponseEntity.ok(this.bookMarksService.getBookMarks(user.getName()));
    }
}
