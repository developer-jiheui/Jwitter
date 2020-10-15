package com.jwt.jwitter.models;

import lombok.Data;

@Data
public final class User {
    private final int id;

    private final String email;

    private final String password;

    private final String username;
}
