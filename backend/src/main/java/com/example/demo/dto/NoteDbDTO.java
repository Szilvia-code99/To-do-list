package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteDbDTO {
    private String title;
    private String text;
    private String category;
    private String textColor;
    private String color;
    private Boolean pinned;
}
