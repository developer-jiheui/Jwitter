package com.jwt.jwitter.config.jwt;

import com.jwt.jwitter.models.User;
import com.jwt.jwitter.models.mappers.UserMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository to work with credentials.
 */
@Repository
public class CredentialsRepository {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private UserMapper mapper;

    @Transactional(readOnly = true)
    public Optional<User> loadByEmail(final String email) {
        final List<User> users = this.template.query("SELECT * from users where email = ? LIMIT 1", new Object[]{email}, this.mapper);
        return Optional.ofNullable(users.isEmpty() ? null : users.get(0));
    }
}
