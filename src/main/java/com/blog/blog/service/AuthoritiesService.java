package com.blog.blog.service;

import com.blog.blog.model.Authorities;
import com.blog.blog.model.User;
import com.blog.blog.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

    AuthoritiesRepository authoritiesRepository;

    @Autowired
    public void setAuthoritiesRepository(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    public void save(Authorities authorities){
        authoritiesRepository.save(authorities);
    }
    public Authorities findByName(String name){
        return authoritiesRepository.findByName(name);
    }

}
