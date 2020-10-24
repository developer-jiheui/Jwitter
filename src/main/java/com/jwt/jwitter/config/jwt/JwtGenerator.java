package com.jwt.jwitter.config.jwt;

import org.springframework.security.core.Authentication;

/**
 * Generate JWT.
 */
public interface JwtGenerator {

    String generateJwtToken(Authentication authentication);
}
