package com.jwt.jwitter.web.service;

import com.jwt.jwitter.config.jwt.JwtGenerator;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.SignInDto;
import com.jwt.jwitter.web.dto.in.SignUpDto;
import com.jwt.jwitter.web.repository.UsersRepository;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JwtGenerator jwtGenerator;

    private final UsersRepository repository;

    @Transactional(readOnly = true)
    public String signIn(final SignInDto signInDto) {
        var authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return this.jwtGenerator.generateJwtToken(authentication);
    }

    @Transactional
    public void signUp(final SignUpDto signInDto) {
        this.repository.save(new User(
            signInDto,
            this.encoder.encode(signInDto.getPassword())
        ));
    }
}
