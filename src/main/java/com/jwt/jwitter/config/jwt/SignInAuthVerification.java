package com.jwt.jwitter.config.jwt;

import com.jwt.jwitter.web.dto.Credentials;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Verify that credentials match with db.
 */
@Service
@AllArgsConstructor
public class SignInAuthVerification {

    private final AuthenticationManager authenticationManager;

    private final JwtGenerator jwtGenerator;

    public String verify(final Credentials credentials) {
        var authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return this.jwtGenerator.generateJwtToken(authentication);
    }
}
