package com.example.demo.mapper;

import com.example.demo.dto.LogDTO;
import com.example.demo.model.Log;
import com.example.demo.model.Note;
import com.example.demo.model.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogMapper {
    public static LogDTO modelToDto(Log log) {
        return new LogDTO(
                log.getId(),
                String.valueOf(log.getUser().getId()),
                String.valueOf(log.getNote().getId()),
                log.getCompletedOn().toString());

    }

    public static List<LogDTO> modelsToDtos(List<Log> logs) {
        List<LogDTO> list = new ArrayList<>(logs.size());
        for (Log log : logs) {
            list.add(modelToDto(log));
        }
        return list;
    }

    public static Log dtoToModel(LogDTO logDTO , User user, Note note) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return new Log(null, user, note, null);
    }

}
