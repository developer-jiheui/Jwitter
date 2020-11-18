package com.jwt.jwitter.web.service;

import com.jwt.jwitter.config.jwt.SignInAuthVerification;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.SignInDto;
import com.jwt.jwitter.web.dto.in.SignUpDto;
import com.jwt.jwitter.web.repository.UsersRepository;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
@Repository
public class AuthService {

    private final PasswordEncoder encoder;

    private final UsersRepository repository;

    private final SignInAuthVerification verification;

    @Transactional(readOnly = true)
    public String signIn(final SignInDto signInDto) {
        return this.verification.verify(signInDto);
    }

    @Transactional
    public User signUp(final SignUpDto signUpDto) {
        return this.repository.save(new User(
            signUpDto,
            this.encoder.encode(signUpDto.getPassword())
        ));
    }

    @Transactional
    public User getUserByEmail(final String email) {
        return this.repository.getUserByEmail(email);
    }
}
