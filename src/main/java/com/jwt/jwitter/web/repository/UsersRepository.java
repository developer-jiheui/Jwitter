package com.jwt.jwitter.web.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jwt.jwitter.config.jwt.JwtProvider;
import com.jwt.jwitter.models.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.jwt.jwitter.models.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    JwtProvider provider;

    public boolean exists(final int userId) {
        return this.jdbcTemplate.queryForObject("SELECT count(*) from users where id = ?", Integer.class, userId) != 0;
    }

    public void updateAvatar(final int userId, final String fileId) {
        this.jdbcTemplate.update("UPDATE users set avatar=? where id=?", fileId, userId);
    }

    public User save(final User user) {
        final KeyHolder holder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users (email,password,username,birthday) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setDate(4, new Date(user.getBirthday().getTime()));
            return preparedStatement;
        }, holder);
        user.setId(Integer.parseInt(holder.getKeys().get("id").toString()));
        return user;
    }

    public User updateUser(final User user) {
        System.out.println("update" + user.toString());
        this.jdbcTemplate.update(
            "UPDATE users SET username=?,birthday=?,avatar=?,bio=?,location=?,website=? WHERE id = ?",
            user.getUsername(),
            new Date(user.getBirthday().getTime()),
            user.getAvatar(),
            user.getBio(),
            user.getLocation(),
            user.getWebsite(),
            user.getId()
        );
        System.out.println("UPDATED USER???");
        return getUser(user.getEmail());
    }

    public User getUser(String email) {
        return this.jdbcTemplate.queryForObject("SELECT * from users where email='"+ email + "';", new UserMapper());
    }
}
