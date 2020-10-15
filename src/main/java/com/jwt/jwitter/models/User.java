package com.jwt.jwitter.models;

import com.jwt.jwitter.web.dto.in.SignUpDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class User {
    private final int id;

    private final String email;

    private final String password;

    private final String username;

    public User(final SignUpDto signUpDto, final String encodedPassword) {
        this.id = -1;
        this.email = signUpDto.getEmail();
        this.password = encodedPassword;
        this.username = signUpDto.getUsername();
    }

}
