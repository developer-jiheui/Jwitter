package com.jwt.jwitter.models;

import com.jwt.jwitter.web.dto.in.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Db user.
 */
@AllArgsConstructor
@Data
public final class Post {
	private int id;

    private int user_id;
    private String content;
    private String photo;
    private int shares;
    private int likes;
    private int comments;
    private int reply_to_id;


    public Post(final PostDto postDto) {
        this.id = -1;
        this.content = postDto.getContent();
        this.photo = postDto.getPhoto();
        this.user_id = postDto.getUser_id();
        this.shares = postDto.getShares();
        this.likes = postDto.getLikes();
        this.comments = postDto.getComments();
        this.reply_to_id = postDto.getReply_to_id();
    }
    public Post(final String content, final String photo) {
        this.content = content;
        this.photo = photo;
    }
}
