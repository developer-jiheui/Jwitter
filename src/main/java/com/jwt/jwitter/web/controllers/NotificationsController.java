package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.service.NotificationsService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationsController {

    @Autowired
    private NotificationsService service;

    @GetMapping
    public ResponseEntity<List<?>> notifications(final Principal user) {
        return ResponseEntity.ok(this.service.notifications(user.getName()));
    }
}
