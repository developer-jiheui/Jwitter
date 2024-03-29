package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.service.AvatarsService;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/avatars")
public class AvatarsController {

    @Autowired
    private AvatarsService avatarsService;

    /**
     * Update avatar for user.
     * @param file Avatar
     * @param userId User id
     * @return Response
     */
    @PostMapping("/upload/{id}")
    public ResponseEntity<?> upload(
        @RequestParam("file") final MultipartFile file,
        @PathVariable(name = "id") final int userId
    ) {
        try {
            if (this.avatarsService.upload(file, userId)) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Bad request"));
            }
        } catch (final IOException exc) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/photoUpload")
    public ResponseEntity<?> photoUpload(
            @RequestParam("file") final MultipartFile file
    ) {
        try {
            System.out.println("Uploaded"+ResponseEntity.ok(this.avatarsService.photoUpload(file)));
            return ResponseEntity.ok(this.avatarsService.photoUpload(file));
        } catch (final IOException excp) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
