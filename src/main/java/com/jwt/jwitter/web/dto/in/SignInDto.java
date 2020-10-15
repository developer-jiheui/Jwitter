package com.jwt.jwitter.web.dto.in;

import lombok.Data;

@Data
public final class SignInDto {

    private final String email;

    private final String password;

}
