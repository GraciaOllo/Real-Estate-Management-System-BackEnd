package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
@Bean
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public void saveUser(Users user){
         userRepository.save(user);
    }
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }


}
