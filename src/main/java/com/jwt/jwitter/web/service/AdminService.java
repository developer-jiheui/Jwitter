package com.jwt.jwitter.web.service;

import com.jwt.jwitter.web.dto.out.AdminUsersDto;
import com.jwt.jwitter.web.dto.out.ReportsDto;
import com.jwt.jwitter.web.repository.AdminRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<ReportsDto> reports() {
        return this.adminRepository.reports();
    }

    public List<AdminUsersDto> users() {
        return this.adminRepository.users();
    }

    public void enable(final int id) {
        this.adminRepository.enable(id);
    }

    public void disable(final int id) {
        this.adminRepository.disable(id);
    }

    public void enableTweet(final int id) {
       this.adminRepository.enableTweet(id) ;
    }

    public void disableTweet(final int id) {
        this.adminRepository.disableTweet(id) ;
    }

    public boolean enabled(final String email) {
        return this.adminRepository.enabled(email);
    }
}
