package com.jwt.jwitter.web.dto.in;

import lombok.Data;

@Data
public class PostDto {

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	private  String photo;
    private  String content;
    private  int user_id;
    private  int comments;
	private  int likes;
	private  int shares;
	private  int reply_to_id;



}
