package com.example.demo.mapper;

import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Note;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public static UserDTO modelToDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getPassword()
        );
    }

    public static List<UserDTO> modelsToDtos(List<User> users) {
        List<UserDTO> list = new ArrayList<>(users.size());
        for (User user : users) {
            list.add(modelToDto(user));
        }
        return list;
    }
}
