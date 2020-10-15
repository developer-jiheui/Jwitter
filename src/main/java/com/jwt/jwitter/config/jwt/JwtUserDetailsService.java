package com.jwt.jwitter.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private CredentialsRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return this.repository.loadByEmail(email)
            .map(UserDetailsImpl::new)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("No user with email %s", email)));
    }
}
