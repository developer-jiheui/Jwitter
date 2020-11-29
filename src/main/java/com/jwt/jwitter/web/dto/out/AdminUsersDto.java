package com.jwt.jwitter.web.dto.out;

import com.jwt.jwitter.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminUsersDto {

    private final String email;

    private final String username;

    private final boolean disabled;

    private final int id;

    public AdminUsersDto(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.disabled = !user.isEnabled();
        this.id = user.getId();
    }
}
