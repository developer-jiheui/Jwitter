package com.jwt.jwitter.config.jwt;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.SignUpDto;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
final class JwtProviderTestCase {

    private JwtProvider jwtProvider;

    @BeforeAll
    public void init() {
        this.jwtProvider = new JwtProvider("my_super_secret", 1800);
    }

    @Test
    void testUserFromJwt() {
        //given
        var mock = Mockito.mock(Authentication.class);
        var email = "almas@gmail.com";
        var user = new User(
            new SignUpDto(
                email,
                "hello",
                "almas",
                new Date()
            ), "hello"
        );
        //when
        Mockito.when(mock.getPrincipal()).thenReturn(new UserDetailsImpl(user));
        var jwtToken = this.jwtProvider.generateJwtToken(mock);
        Assertions.assertEquals(
            email,
            this.jwtProvider.getUserNameFromJwtToken(jwtToken),
            "Username from token should be equal to predefined email"
        );

    }
}
