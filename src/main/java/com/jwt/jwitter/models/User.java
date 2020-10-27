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

    private final String email;

    private final String password;

    private final String username;

    private final Date birthday;

    private final String avatar;

    private final String bio;

    private final String location;

    private final String website;


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

}
