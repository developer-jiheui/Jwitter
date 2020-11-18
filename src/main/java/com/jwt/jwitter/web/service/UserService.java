package com.jwt.jwitter.web.service;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.repository.UsersRepository;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

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

    @Transactional(readOnly = true)
    public List<User> searchUsers(final String word) {
        return this.usersRepository.searchUsers(word);
    }

    @Transactional
    public User updateUser(final User user) {
        return this.usersRepository.updateUser(user);
    }

    @Transactional
    public List<User> getFollowing(final int userid) {
        return this.usersRepository.getFollowing(userid);
    }
    @Transactional
    public List<User> getFollower(final int userid) {return this.usersRepository.getFollower(userid);
    }

    @Transactional
    public boolean isSameUserName(final User user) { return this.usersRepository.isSameUserName(user);}
}
