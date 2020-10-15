package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.models.User;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(final User user) {
        this.jdbcTemplate.update(
            "INSERT INTO users (email,password,username,birthday) values (?,?,?,?)",
            user.getEmail(), user.getPassword(), user.getUsername(), OffsetDateTime.now()
        );
    }
}
