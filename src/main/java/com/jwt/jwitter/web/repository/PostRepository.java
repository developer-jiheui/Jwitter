


package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.models.mappers.PostMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private PostMapper postMapper;

    public void updatePhoto(final int postId, final String fileId) {
        this.jdbcTemplate.update("UPDATE tweet set photo=? where id=?", fileId, postId);
    }

    public Post save(final Post post) {
        this.jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tweet (user_id,content) values (?,?)");
            preparedStatement.setInt(1, post.getUser_id());
            preparedStatement.setString(2, post.getContent());
            return preparedStatement;
        });
        return post;
    }
    
    public List<Post> get(final int user_id) {
        return this.jdbcTemplate.query(
                "select * from tweet where user_id ="+user_id,
                new PostMapper());
    }
}