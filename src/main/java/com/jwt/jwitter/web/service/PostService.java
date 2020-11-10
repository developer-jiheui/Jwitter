package com.jwt.jwitter.web.service;

import com.jwt.jwitter.models.Comment;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.web.dto.in.PostDto;
import com.jwt.jwitter.web.repository.PostRepository;

import java.util.List;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Comment> getPostsByUser(final int user_id) {
        return this.repository.getPostsByUser(user_id);
    }

    @Transactional(readOnly = true)
    public List<Comment> getPostsByFollow(final int user_id) {
        return this.repository.getPostsByUser(user_id);
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByTweetId(final int tweet_id) {
        return this.repository.getCommentsByTweetId(tweet_id);
    }

    @Transactional
    public Post tweet(final PostDto postDto) {
        return this.repository.save(new Post(postDto));
    }
}
