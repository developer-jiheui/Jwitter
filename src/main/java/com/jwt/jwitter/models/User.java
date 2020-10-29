package com.jwt.jwitter.models;

import com.jwt.jwitter.web.dto.in.SignUpDto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Db user.
 */
@Data
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue
    private int id;

    private  String email;

    private  String password;

    private  String username;

    private  Date birthday;

    private  String avatar;

    private  String bio;

    private  String location;

    private  String website;


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
