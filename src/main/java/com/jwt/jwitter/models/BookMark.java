package com.jwt.jwitter.models;

import lombok.Data;

@Data
public final class BookMark {

    private final User creator;

    private final Post tweet;
}
