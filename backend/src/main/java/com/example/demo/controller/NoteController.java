package com.example.demo.controller;

import com.example.demo.dto.NoteDTO;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @CrossOrigin
    @GetMapping("/notes")
    public List<NoteDTO> getAllNotes() throws SQLException, ClassNotFoundException {
        return noteService.getAllNotes();
    }

    @CrossOrigin
    @GetMapping("/notes/{category}")
    public List<NoteDTO> getNotesByCategory(@PathVariable String category) throws ClassNotFoundException {
        return noteService.getNotesByCategory(category);
    }

    @CrossOrigin
    @GetMapping("/note/{id}")
    public NoteDTO getNoteById(@PathVariable int id) {
        if (noteService.getNoteById(id).isPresent()) {
            return NoteMapper.modelToDto(noteService.getNoteById(id).get());
        }
        throw new EntityNotFoundException(id);
    }

    @CrossOrigin
    @PutMapping("/note")
    public void updateNoteCategory(@RequestBody NoteDTO noteDTO) throws SQLException, ClassNotFoundException {
         noteService.updateNote(noteDTO);
    }

    @CrossOrigin
    @PostMapping("/note")
    public void addNote(@RequestBody NoteDTO noteDTO) throws ClassNotFoundException {
        noteService.addNote(noteDTO);
    }

    @CrossOrigin
    @DeleteMapping("/note/{id}")
    public void deleteNote(@PathVariable int id) throws ClassNotFoundException {
        noteService.deleteNote(id);
    }
}