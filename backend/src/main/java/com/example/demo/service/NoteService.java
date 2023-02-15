package com.example.demo.service;

import com.example.demo.JdbcHelper;
import com.example.demo.dto.NoteDTO;
import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;


    public void deleteNote(int id) throws ClassNotFoundException {
        this.noteRepository.deleteNote(id);
    }

    public void addNote(NoteDTO noteDTO) throws ClassNotFoundException {
        this.noteRepository.addNote(noteDTO);
    }

    public void updateNote(NoteDTO noteDTO) throws ClassNotFoundException {
        this.noteRepository.updateNote(noteDTO);
    }

    public Note getNoteById(int id) throws ClassNotFoundException {
       return this.noteRepository.getNoteById(id);
    }

    public List<NoteDTO> getNotesByCategory(String category) throws ClassNotFoundException {
        return this.noteRepository.getNotesByCategory(category);

    }

    public String getCategoryOfNote(int id) throws ClassNotFoundException {
       return this.noteRepository.getCategoryOfNote(id);
    }

    public List<NoteDTO> getAllNotes() throws SQLException, ClassNotFoundException {
      return  this.noteRepository.getAllNotes();
    }
}
