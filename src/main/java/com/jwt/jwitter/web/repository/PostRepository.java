


package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.models.Comment;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.mappers.CommentMapper;
import com.jwt.jwitter.models.mappers.PostMapper;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Stop creating beans with new operand , use Autowire
    @Autowired
    private CommentMapper mapper;

    public void updatePhoto(final int postId, final String fileId) {
        this.jdbcTemplate.update("UPDATE tweet set photo=? where id=?", fileId, postId);
    }

    public Post save(final Post post) {
        this.jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO tweet (user_id,content,photo) values (?,?,?)");
            preparedStatement.setInt(1, post.getUser_id());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getPhoto());
            return preparedStatement;
        });
        return post;
    }

    public List<Comment> getPostsByUser(final int user_id) {
        return this.jdbcTemplate.query(
                "select * from tweet t left join users u on u.id =t.user_id "+
                "where t.user_id in (select follow_user_id from follow where user_id=" + user_id+")",
                this.mapper);
    }

    public List<Comment> getCommentsByTweetId(final int tweet_id) {
        return this.jdbcTemplate.query(
                "select * from tweet t left join users u on u.id =t.user_id\n" +
                        "where reply_to_id =" + tweet_id, this.mapper);
    }
}