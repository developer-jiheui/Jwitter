package com.jwt.jwitter.web.dto.in;

import com.jwt.jwitter.web.dto.Credentials;
import lombok.Data;

@Data
public final class SignInDto implements Credentials {

    private final String email;

    private final String password;

}
