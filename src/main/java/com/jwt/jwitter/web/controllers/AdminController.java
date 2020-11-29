package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.dto.out.AdminUsersDto;
import com.jwt.jwitter.web.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin", produces = "application/json")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<AdminUsersDto>> users() {
        return ResponseEntity.ok(this.adminService.users());
    }

    @PostMapping("/users/{id}/enable")
    public ResponseEntity<?> enable(@PathVariable final int id) {
        this.adminService.enable(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/users/{id}/disable")
    public ResponseEntity<?> disable(@PathVariable final int id) {
        this.adminService.disable(id);
        return ResponseEntity.noContent().build();
    }

}
