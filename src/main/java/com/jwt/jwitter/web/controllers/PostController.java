package com.jwt.jwitter.web.controllers;

import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.PostDto;
import com.jwt.jwitter.web.service.AuthService;
import com.jwt.jwitter.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Auth controller.
 */
@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public final class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private AuthService authService;

    @PostMapping("/tweet")
    public ResponseEntity<?> tweet(@RequestBody PostDto postDto) {
        try {
            final UserDetails auth = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final String email = auth.getUsername();
            User u = authService.getUserByEmail(email);
            postDto.setUser_id(u.getId());
            if (postDto.getReply_to_id() > 0) {
                Post p = this.postService.getPostById(postDto.getReply_to_id());
                this.postService.addComment(p.getId(), p.getComments() + 1);
            }
            return ResponseEntity.ok(this.postService.tweet(postDto));
        } catch (final Exception exc) {
            return ResponseEntity.badRequest().body(
                Map.of("message", exc.getMessage())
            );
        }
    }

    @GetMapping("/tweets/{user_id}")
    public ResponseEntity<?> getTweets(@PathVariable("user_id") int user_id) {
        try {
            return ResponseEntity.ok(this.postService.getPostsByUser(user_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/tweetsAndReplies/{user_id}")
    public ResponseEntity<?> getTweetsAndReplies(@PathVariable("user_id") int user_id) {
        try {
            return ResponseEntity.ok(this.postService.getTweetsAndReplies(user_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/tweetslike/{user_id}")
    public ResponseEntity<?> getLikes(@PathVariable("user_id") int user_id) {
        try {
            return ResponseEntity.ok(this.postService.getLikes(user_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }



    @GetMapping("/comments/{tweet_id}")
    public ResponseEntity<?> getCommentsByTweetId(@PathVariable("tweet_id") int tweet_id) {
        try {
            return ResponseEntity.ok(this.postService.getCommentsByTweetId(tweet_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/likeNShare/{tweet_id}")
    public ResponseEntity<?> getLikeNShare(@PathVariable("tweet_id") int tweet_id) {
        try {
            final UserDetails auth = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final String email = auth.getUsername();
            User u = authService.getUserByEmail(email);
            int user_id = u.getId();
            return ResponseEntity.ok(this.postService.getLikeNShare(user_id, tweet_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/toggleShare/{tweet_id}/{toggle}")
    public ResponseEntity<?> toggleShare(@PathVariable("tweet_id") int tweet_id, @PathVariable("toggle") boolean toggle) {
        try {
            final UserDetails auth = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final String email = auth.getUsername();
            User u = authService.getUserByEmail(email);
            int user_id = u.getId();
            return ResponseEntity.ok(this.postService.toggleShare(user_id, tweet_id, toggle));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/toggleLike/{tweet_id}/{toggle}")
    public ResponseEntity<?> toggleLike(@PathVariable("tweet_id") int tweet_id, @PathVariable("toggle") boolean toggle) {
        try {
            final UserDetails auth = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final String email = auth.getUsername();
            User u = authService.getUserByEmail(email);
            int user_id = u.getId();
            return ResponseEntity.ok(this.postService.toggleLike(user_id, tweet_id, toggle));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }

    //
    @GetMapping("/posts/{user_id}")
    public ResponseEntity<?> getPostsByFollow(@PathVariable("user_id") int user_id) {
        try {
            return ResponseEntity.ok(this.postService.getPostsByFollow(user_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/tweets/{tweet_id}")
    public ResponseEntity<?> deletePost(@PathVariable("tweet_id") int tweet_id) {
        try {
            return ResponseEntity.ok(this.postService.deletePost(tweet_id));
        } catch (final AuthenticationException exc) {
            return new ResponseEntity<>(Map.of("message", "Bad credentials"), HttpStatus.FORBIDDEN);
        }
    }


}
