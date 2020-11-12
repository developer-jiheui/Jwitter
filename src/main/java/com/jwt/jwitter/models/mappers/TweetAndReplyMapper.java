package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import com.jwt.jwitter.models.Comment;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TweetAndReplyMapper implements RowMapper<Comment> {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private AvatarUrlProvider avatarUrlProvider;

    @Override
    public Comment mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        Post p = postMapper.mapRow(rs, rowNum);
        User u = new User(
                rs.getInt("user_id"),
                rs.getString("username"),
                this.avatarUrlProvider.normalizeUrl(rs.getString("avatar"))
        );

        return new Comment(u, p);
    }
}
