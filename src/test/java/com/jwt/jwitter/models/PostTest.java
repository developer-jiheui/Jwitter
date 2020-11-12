package com.jwt.jwitter.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    Post p ;
    @BeforeEach
    void setUp() {
        p= new Post("Test Post", "picture.png",false);
    }


    @Test
    void setPhoto() {
        String pic="me.jpg";
        p.setPhoto(pic);
        Assertions.assertEquals(pic, p.getPhoto());
    }

    @Test
    void getContent() {
        Assertions.assertEquals("Test Post", p.getContent());
    }

    @Test
    void isBookMarked() {
        Assertions.assertEquals(false, p.isBookMarked());
    }
}