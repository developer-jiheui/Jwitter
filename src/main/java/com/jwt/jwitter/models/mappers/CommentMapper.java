package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import com.jwt.jwitter.models.Comment;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * Db mapper for comment.
 */
@Service
public final class CommentMapper implements RowMapper<Comment> {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private AvatarUrlProvider avatarUrlProvider;

    @Override
    public Comment mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        Post p = postMapper.mapRow(rs, rowNum);
        User u = new User(
            rs.getInt("user_id"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("username"),
            rs.getDate("birthday"),
            this.avatarUrlProvider.normalizeUrl(rs.getString("avatar")),
            rs.getString("bio"),
            rs.getString("location"),
            rs.getString("website"),
            rs.getDate("created_at"),
            this.avatarUrlProvider.normalizeUrl(rs.getString("coverPhoto")),
            true
        );
        return new Comment(u, p);
    }
}
