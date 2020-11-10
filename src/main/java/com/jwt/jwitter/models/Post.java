package com.jwt.jwitter.models;

import com.jwt.jwitter.web.dto.in.PostDto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getReply_to_id() {
        return reply_to_id;
    }

    public void setReply_to_id(int reply_to_id) {
        this.reply_to_id = reply_to_id;
    }

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

}
