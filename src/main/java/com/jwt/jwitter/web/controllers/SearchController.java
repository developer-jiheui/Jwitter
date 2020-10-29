package com.jwt.jwitter.web.controllers;

import org.springframework.data.jpa.domain.Specification;
import com.jwt.jwitter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.jwt.jwitter.web.repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;

import com.jwt.jwitter.web.specification.UsersSpecification;


import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;

import com.jwt.jwitter.web.repository.UserRepositorySpec;
import java.util.List;


//public class Sear
// chController {


@RestController
@RequestMapping(path="/usernamesearch")
public class SearchController {

    @Autowired
    private UserRepositorySpec userRepo;

    @GetMapping
    public List<User> getAll(UsersSpecification.nameSpec spec) {
            return userRepo.findAll(spec);
    }

}
