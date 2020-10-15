package com.jwt.jwitter.config.jwt;

import org.springframework.security.core.Authentication;

public interface JwtGenerator {

    String generateJwtToken(Authentication authentication);
}
