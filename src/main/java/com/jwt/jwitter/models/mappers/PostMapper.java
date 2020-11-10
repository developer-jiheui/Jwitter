package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
            rs.getInt("reply_to_id")
        );
    }
}
