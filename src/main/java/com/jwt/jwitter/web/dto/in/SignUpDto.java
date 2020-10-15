package com.jwt.jwitter.web.dto.in;

import lombok.Data;

@Data
public final class SignUpDto {

    private final String email;

    private final String password;

    private final String confirmPassword;

    private final String username;

}
