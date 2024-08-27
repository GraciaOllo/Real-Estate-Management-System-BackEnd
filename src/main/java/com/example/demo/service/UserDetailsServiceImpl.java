package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Users;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users u = userRepository.findByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .roles("ADMIN","TENANT","AGENT")
                .build();
    }
}
