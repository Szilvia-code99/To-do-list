package com.example.demo.controller;

import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.NoteDbDTO;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @CrossOrigin
    @GetMapping("/notes")
    public List<NoteDTO> getAllNotes() {
        return NoteMapper.modelsToDtos(noteService.getAllNotes());
    }

    @CrossOrigin
    @GetMapping("/notes/{category}")
    public List<NoteDTO> getNotesByCategory(@PathVariable String category) {
        return NoteMapper.modelsToDtos(noteService.getNotesByCategory(category));
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
    public Note updateNoteCategory(@RequestBody Note note) {
       return noteService.saveOrUpdate(note);
    }

    @CrossOrigin
    @PostMapping("/note")
    public Note addNote(@RequestBody Note note) {
        noteService.saveOrUpdate(note);
        return note;
    }

    @CrossOrigin
    @DeleteMapping("/note/{id}")
    public void deleteNote(@PathVariable int id) {
        noteService.deleteNote(id);
    }
}