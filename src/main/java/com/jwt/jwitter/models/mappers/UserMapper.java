package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Db mapper for user.
 */
public final class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new User(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("username"),
            rs.getDate("birthday"),
            rs.getString("avatar"),
            rs.getString("bio"),
            rs.getString("location"),
            rs.getString("website")
        );
    }
}
