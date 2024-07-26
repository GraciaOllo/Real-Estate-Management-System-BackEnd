package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public Optional<Users> login(String email, String password) {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().login(email, password)) {
            return user;
        }
        return Optional.empty();
    }

    public void logout(Users user) {
        user.logout();
    }

    public boolean verifyLogin(Users user) {
        return user.verifyLogin();
    }

}
