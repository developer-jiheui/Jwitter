package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.web.dto.in.ReportDto;
import com.jwt.jwitter.web.dto.in.SignInDto;
import com.jwt.jwitter.web.dto.in.SignUpDto;
import com.jwt.jwitter.web.service.AuthService;
import com.jwt.jwitter.web.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * Report controller.
 */
@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody @Valid ReportDto reportDto) {
        if(reportDto.getReport_content().length()<5){
            return ResponseEntity.badRequest().body(
                    Map.of("message", "Please Write a bit more")
            );
        }
        try {
            return ResponseEntity.ok(this.reportService.reportPost(reportDto));
        } catch (final Exception exc) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", exc.getMessage())
            );
        }
    }




}




