package com.jwt.jwitter.web.service;

import com.jwt.jwitter.models.Post;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.web.dto.in.PostDto;
import com.jwt.jwitter.web.dto.in.SignInDto;
import com.jwt.jwitter.web.dto.in.SignUpDto;
import com.jwt.jwitter.web.repository.PostRepository;
import com.jwt.jwitter.web.repository.UsersRepository;
import lombok.Data;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class PostService {

    private final PostRepository repository = new PostRepository();

    @Transactional(readOnly = true)
    public List<Post> get(final int user_id) {
        return this.repository.get(user_id);
    }

    @Transactional
    public Post tweet(final PostDto postDto) {
        return this.repository.save(new Post(postDto));
    }
}
