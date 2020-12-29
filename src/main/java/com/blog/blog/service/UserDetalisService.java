package com.blog.blog.service;

import com.blog.blog.model.Authorities;
import com.blog.blog.model.User;
import com.blog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service("userDetailsService")
class UsersDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);


        if(user == null){
            throw new UsernameNotFoundException("NEM TALÁLOM A FELHASZNÁÁÁLÓÓT: " + username);
        }
        return user;
    }
}
