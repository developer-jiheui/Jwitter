package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import com.jwt.jwitter.models.BookMark;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public final class BookMarkMapper implements RowMapper<BookMark> {

    @Autowired
    private AvatarUrlProvider urlProvider;

    @Override
    public BookMark mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new BookMark(
            new User(
                rs.getInt("user_id"),
                rs.getString("username"),
                this.urlProvider.normalizeUrl(rs.getString("avatar"))
            ),
            new Post(
                rs.getString("content"),
                rs.getString("tweet_photo")
            )
        );
    }
}
