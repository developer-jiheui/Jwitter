package com.jwt.jwitter.web.dto.in;

import com.jwt.jwitter.web.dto.IntUserDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public final class UserDto implements IntUserDto {
//    private final @Email String email;
//    private final @Size(min = 5) String password;
    private final @Size(min = 5) String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final @NotNull Date birthday;
    private final String avatar;
    private final String bio;
    private final String location;
    private final String website;

}
