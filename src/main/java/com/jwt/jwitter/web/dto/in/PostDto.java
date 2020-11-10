package com.jwt.jwitter.web.dto.in;

import lombok.Data;

@Data
public class PostDto {

	private final String photo;
	private final String content;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	private int user_id;
	private final int comments;
	private final int likes;
	private final int shares;
	private final int reply_to_id;
}
