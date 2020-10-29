package com.jwt.jwitter.web.service;

import com.jwt.jwitter.config.jwt.JwtProvider;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.repository.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class UserService {

    @Autowired
    private final UsersRepository usersRepository;
    private JwtProvider jwtProvider;
    SecurityContext securityContext;

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String username = userDetails.getUsername();
        return this.usersRepository.getUser(username);
    }

    @Transactional
    public User saveUser(final User user) {
        return this.usersRepository.save(user);
    }
}
