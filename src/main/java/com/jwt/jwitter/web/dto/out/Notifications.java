package com.jwt.jwitter.web.dto.out;

import lombok.Data;

@Data
public final class Notifications {

    private final String username;

    private final String tweet;
}
