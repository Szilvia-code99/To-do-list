package com.example.demo.service;
import com.example.demo.dto.LogDTO;
import com.example.demo.dto.NoteDTO;
import com.example.demo.mapper.LogMapper;
import com.example.demo.model.Log;
import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.repository.LogRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class LogService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogRepository logRepository;

    public void saveOrUpdate(LogDTO logDTO) throws ClassNotFoundException {

        //first select the requested user and note
        Note note = noteRepository.getNoteById(Integer.valueOf(logDTO.getNoteId()));
        User user = userRepository.getUserById(Integer.valueOf(logDTO.getUserId()));

        //check if note has not been completed
        if (noteRepository.getCategoryOfNote(note.getId()) != "Done") {
            Log log = LogMapper.dtoToModel(logDTO, user,note);
            this.logRepository.saveOrUpdate(log);

            //update category of note
            NoteDTO noteDTO = new NoteDTO(note.getId(),note.getTitle(),note.getText(), "Done",note.getTextColor(),note.getColor(),note.getPinned());
            this.noteRepository.updateNote(noteDTO);
        }
    }

    public List<LogDTO> getAllLogs() throws ClassNotFoundException {
        return this.logRepository.getAllLogs();
    }
}
