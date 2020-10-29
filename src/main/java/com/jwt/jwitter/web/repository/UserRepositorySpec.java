package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.models.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorySpec extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

}

