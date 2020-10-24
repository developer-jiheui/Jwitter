package com.jwt.jwitter.web.dto.in;

import com.jwt.jwitter.web.dto.Credentials;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public final class SignUpDto implements Credentials {

    private final @Email String email;

    private final @Size(min = 5) String password;

    private final @Size(min = 5) String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final @NotNull Date birthday;

}
