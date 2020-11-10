package com.jwt.jwitter.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public final class Comment{

    private final User user;
    private final Post post;

    /* getters here */
}



