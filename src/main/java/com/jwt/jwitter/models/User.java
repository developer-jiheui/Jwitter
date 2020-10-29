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
    private  String email;
    private  String password;
    private  String username;
    private  Date birthday;
    private  String avatar;
    private  String bio;
    private  String location;
    private  String website;
    private  Date joinday;

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

    public String getAvatar() {return avatar; }
    public void setAvatar(String avatar) {this.avatar = avatar;}

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {this.email = email;}

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {this.password = password;}

    public String getBio() {
        return this.bio;
    }
    public void setBio(String bio) {this.bio = bio;}

    public String getLocation() {return this.location; }
    public void setLocation(String location) {this.location = location;}

    public String getWebsite() {
        return this.website;
    }
    public void setWebsite(String website) {this.website = website;}

    public Date getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Date bday) {this.birthday = bday;}

    public Date getJoinday() {
        return this.joinday;
    }
    public void setJoinday(Date jday) {this.joinday = jday;}
}
