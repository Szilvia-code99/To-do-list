package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteDTO {
    private int id;
    private String title;
    private String text;
    private String category;
    private String ownerId;
    private String textColor;
    private String color;
    private Boolean pinned;
}
