package com.example.demo.dto;

import com.example.demo.model.Note;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogDTO {
    private int id;
    private String userId;
    private String noteId;
}
