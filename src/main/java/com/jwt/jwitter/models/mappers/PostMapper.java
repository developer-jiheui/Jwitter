package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.models.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * Db mapper for user.
 */
@Service
public final class PostMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Post(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getString("content"),
            rs.getString("photo"),
            rs.getInt("shares"),
            rs.getInt("likes"),
            rs.getInt("comments"),
            rs.getInt("reply_to_id"),
            false
        );
    }

    public Post postWithBookmark(final ResultSet rs, final int rowNum) throws SQLException {
        return new Post(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getString("content"),
            rs.getString("photo"),
            rs.getInt("shares"),
            rs.getInt("likes"),
            rs.getInt("comments"),
            rs.getInt("reply_to_id"),
            rs.getObject("bookmarked") != null
        );
    }
}
