package com.jwt.jwitter.web.dto;

import java.util.Date;

public class UserDto {
    private int id;
    private  String email;
    private  String password;
    private  String username;
    private Date birthday;
    private  String avatar;
    private  String bio;
    private  String location;
    private  String website;

    public String getAvatar() {return avatar; }
    public void setAvatar() {this.avatar = avatar;}

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
}
