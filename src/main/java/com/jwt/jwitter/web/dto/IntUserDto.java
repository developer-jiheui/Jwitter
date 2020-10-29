package com.jwt.jwitter.web.dto;

import java.util.Date;

public interface IntUserDto {

//    String getEmail();
//    String getPassword();
    String getUsername();
    Date getBirthday();
    String getAvatar();
    String getBio();
    String getLocation();
    String getWebsite();
}
