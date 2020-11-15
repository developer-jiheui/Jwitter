package com.jwt.jwitter.web.service;

import com.jwt.jwitter.models.Comment;
import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.web.dto.in.PostDto;
import com.jwt.jwitter.web.repository.PostRepository;
import java.util.List;
import java.util.Map;
import lombok.Data;
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
    public List<Post> getPostsByUser(final int user_id) {
        return this.repository.getPostsByUser(user_id);
    }

    @Transactional(readOnly = true)
    public List<Comment> getTweetsAndReplies(final int user_id) {
        return this.repository.getTweetsAndReplies(user_id);
    }

    @Transactional(readOnly = true)
    public List<Comment> getLikes(final int user_id) {
        return this.repository.getLikes(user_id);
    }

    @Transactional(readOnly = true)
    public List<Comment> getPostsByFollow(final int user_id) {
        final List<Comment> posts = this.repository.getPostsByFollow(user_id);
        this.repository.checkBookMarks(posts, user_id);
        return posts;
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByTweetId(final int tweet_id) {
        return this.repository.getCommentsByTweetId(tweet_id);
    }

    @Transactional(readOnly = true)
    public Post getPostById(final int post_id) {
        return this.repository.getPostById(post_id);
    }

    @Transactional(readOnly = true)
    public Map getLikeNShare(final int user_id, final int tweet_id) {
        return this.repository.getLikeNShare(user_id, tweet_id);
    }

    @Transactional()
    public int toggleShare(final int user_id, final int tweet_id, boolean toggle) {
        return this.repository.toggleShare(user_id, tweet_id, toggle);
    }

    @Transactional()
    public int toggleLike(final int user_id, final int tweet_id, boolean toggle) {
        return this.repository.toggleLike(user_id, tweet_id, toggle);
    }

    @Transactional
    public Post tweet(final PostDto postDto) {
        return this.repository.save(new Post(postDto));
    }

    @Transactional()
    public void addComment(int id, final int commentNum) {
        this.repository.addComment(id, commentNum);
    }

    @Transactional
    public void createBookMark(final String email, final int postId) {
        this.repository.createBookmark(email, postId);
    }

    @Transactional
    public boolean deletePost(final int tweet_id) { return this.repository.deletePost(tweet_id);}
}
