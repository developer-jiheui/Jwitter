package com.jwt.jwitter.models.mappers;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.Search;
import com.jwt.jwitter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public final class SearchMapper implements RowMapper<Search> {

    @Autowired
    private AvatarUrlProvider urlProvider;

    @Override
    public Search mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Search(
                new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        this.urlProvider.normalizeUrl(rs.getString("avatar"))
                ),
                new Post(
                        rs.getString("content"),
                        rs.getString("tweet_photo"),
                        true
                )
        );
    }
}
