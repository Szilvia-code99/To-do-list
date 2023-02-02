package com.example.demo.controller;

import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.service.NoteService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return UserMapper.modelsToDtos(userService.getAllUsers());
    }

    @CrossOrigin
    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        if (userService.getUserById(id).isPresent()) {
            return UserMapper.modelToDto(userService.getUserById(id).get());
        }
        throw new EntityNotFoundException(id);
    }

    @CrossOrigin
    @PutMapping("/user/{id}")
    public User updateUserCategory(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }
}