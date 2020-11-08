package com.jwt.jwitter.models;

import com.jwt.jwitter.web.dto.in.SignUpDto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Db user.
 */
@Data
@AllArgsConstructor
public final class User {
    private int id;
    private String email;
    private String password;
    private String username;
    private Date birthday;
    private String avatar;
    private String bio;
    private String location;
    private String website;
    private Date joinday;

    public User(final SignUpDto signUpDto, final String encodedPassword) {
        this.id = -1;
        this.email = signUpDto.getEmail();
        this.password = encodedPassword;
        this.username = signUpDto.getUsername();
        this.birthday = signUpDto.getBirthday();
        this.avatar = null;
        this.bio = null;
        this.location = null;
        this.website = null;
    }

    public User(final int id,final String username,final String avatar){
        this.id = id;
        this.username = username;
        this.avatar = avatar;
    }
}
