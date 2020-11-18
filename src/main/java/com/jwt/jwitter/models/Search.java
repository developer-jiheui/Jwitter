package com.jwt.jwitter.models;

import lombok.Data;

@Data
public class Search {

    private final User username;

    private final Post content;
}


