package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.service.BookMarksService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookmarks")
public class BookMarksController {

    @Autowired
    private BookMarksService bookMarksService;

    @GetMapping
    public ResponseEntity<List<?>> bookMarksByUser(final Principal user) {
        return ResponseEntity.ok(this.bookMarksService.getBookMarks(user.getName()));
    }
}
