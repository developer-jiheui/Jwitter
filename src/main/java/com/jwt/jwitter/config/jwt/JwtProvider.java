package com.jwt.jwitter.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public final class JwtProvider {

    @Value("${bezkoder.app.jwtSecret}")
    private final String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationSec}")
    private final int jwtExpirationSec;

    public JwtProvider(
        @Value("${jwt.secret}") final String jwtSecret,
        @Value("${jwt.expiration}") final int jwtExpirationSec
    ) {
        this.jwtSecret = jwtSecret;
        this.jwtExpirationSec = jwtExpirationSec;
    }

    public String generateJwtToken(final Authentication authentication) {

        var userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + (long) this.jwtExpirationSec * 1000L))
            .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
            .compact();
    }

    public String getUserNameFromJwtToken(final String token) {
        return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
