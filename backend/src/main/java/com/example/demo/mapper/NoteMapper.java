package com.example.demo.mapper;

import com.example.demo.dto.NoteDTO;
import com.example.demo.model.Note;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteMapper {
    public static NoteDTO modelToDto(Note note) {
        return new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getText(),
                note.getCategory(),
                note.getTextColor(),
                note.getColor(),
                note.getPinned()
        );
    }

    public static List<NoteDTO> modelsToDtos(List<Note> notes) {
        List<NoteDTO> list = new ArrayList<>(notes.size());
        for (Note note : notes) {
            list.add(modelToDto(note));
        }
        return list;
    }
}
