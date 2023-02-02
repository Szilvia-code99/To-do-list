package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() { return noteRepository.findAll(); }

    public List<Note> getNotesByCategory(String category) { return noteRepository.findAllByCategory(category); }

    public Note saveOrUpdate(Note note)
    {
        return noteRepository.save(note);
    }

    public void deleteNote(int id)
    {
        noteRepository.deleteById(id);
    }

    public Optional<Note> getNoteById(Integer id) { return noteRepository.findById(id); }
}
