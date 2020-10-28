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

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
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


	private int id;

    private String content;

    private String photo;

    private int user_id;

    private int shares;

    private int likes;


    public Post(final PostDto postDto) {
        this.id = -1;
        this.content = postDto.getContent();
        this.photo = postDto.getPhoto();
        this.user_id = postDto.getUser_id();
    }


	public Post(int int1, String string, int int2) {
		// TODO Auto-generated constructor stub
	}


}
