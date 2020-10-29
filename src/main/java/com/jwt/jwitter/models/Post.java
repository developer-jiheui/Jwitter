package com.jwt.jwitter.models;

import com.jwt.jwitter.web.dto.in.PostDto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Db user.
 */
@Data
@AllArgsConstructor
public final class Post {
	private int id;
	private int user_id;
    private String content;
    private String photo;
    private int shares;
    private int likes;


    public Post(final PostDto postDto) {
        this.id = -1;
        this.content = postDto.getContent();
        this.photo = postDto.getPhoto();
        this.user_id = postDto.getUser_id();
    }




}
