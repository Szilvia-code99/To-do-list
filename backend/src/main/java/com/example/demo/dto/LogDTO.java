package com.example.demo.dto;

import com.example.demo.model.Note;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LogDTO {
    private Integer id;
    private String userId;
    private String noteId;
    private String completedOn;
}
