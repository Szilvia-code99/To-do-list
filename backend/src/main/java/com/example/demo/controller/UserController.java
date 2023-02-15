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
    public List<UserDTO> getAllUsers() throws ClassNotFoundException {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable int id) throws ClassNotFoundException {
        return UserMapper.modelToDto(userService.getUserById(id));

    }

    @CrossOrigin
    @PutMapping("/user/{id}")
    public List<UserDTO> getAllUsers(@RequestBody int id) throws ClassNotFoundException {
        return userService.getAllUsers();
    }
}