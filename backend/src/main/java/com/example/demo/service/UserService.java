package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id) throws ClassNotFoundException {
        return userRepository.getUserById(id);
    }

    public List<UserDTO> getAllUsers() throws ClassNotFoundException {
            return userRepository.getAllUsers();
    }
}
