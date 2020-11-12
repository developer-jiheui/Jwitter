package com.jwt.jwitter.web.service;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.repository.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class UserService {

    @Autowired
    private final UsersRepository usersRepository;

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        final String userEmail;
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }
        return this.usersRepository.getUser(userEmail);
    }

    @Transactional
    public User updateUser(final User user) {
        return this.usersRepository.updateUser(user);
    }
}
