package com.jwt.jwitter.web.service;

import com.jwt.jwitter.config.jwt.SignInAuthVerification;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.SignInDto;
import com.jwt.jwitter.web.dto.in.SignUpDto;
import com.jwt.jwitter.web.repository.UsersRepository;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class AuthService {

    private final PasswordEncoder encoder;

    private final UsersRepository repository;

    private final SignInAuthVerification verification;

    @Transactional(readOnly = true)
    public String signIn(final SignInDto signInDto) {
        return this.verification.verify(signInDto);
    }

    @Transactional
    public void signUp(final SignUpDto signInDto) {
        this.repository.save(new User(
            signInDto,
            this.encoder.encode(signInDto.getPassword())
        ));
    }
}
