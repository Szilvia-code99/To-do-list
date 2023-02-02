package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public void saveOrUpdate(User user)
    {
        userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) { return userRepository.findById(id); }
}
