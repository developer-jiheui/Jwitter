package com.jwt.jwitter.web.specification;

import com.jwt.jwitter.models.User;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

//public interface UsersSpecification {
//
//    @Spec(path = "username", spec = LikeIgnoreCase.class)
//    interface username extends Specification<User>{}


    @Or({
            @Spec(path = "username", spec = LikeIgnoreCase.class),
            @Spec(path = "tweet", spec = LikeIgnoreCase.class),
            @Spec(path = "location", spec = LikeIgnoreCase.class)
    })
    public interface UsersSpecification extends Specification<User>{}

