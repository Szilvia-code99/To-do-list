package com.example.demo.controller;

import com.example.demo.dto.LogDTO;

import com.example.demo.mapper.LogMapper;
import com.example.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private LogService logService;

    @CrossOrigin
    @GetMapping("/logs")
    public List<LogDTO> getAllLogs() throws ClassNotFoundException {
        return logService.getAllLogs();
    }

//    @CrossOrigin
//    @GetMapping("/log/{id}")
//    public LogDTO getLogById(@PathVariable int id) {
//        if (logService.getLogById(id).isPresent()) {
//            return LogMapper.modelToDto(logService.getLogById(id).get());
//        }
//        throw new EntityNotFoundException(id);
//    }

    @CrossOrigin
    @PostMapping("/log")
    public void addLog(@RequestBody LogDTO log) throws ClassNotFoundException {
        logService.saveOrUpdate(log);
    }

//    @CrossOrigin
//    @DeleteMapping("/log/{id}")
//    public void deleteLog(@PathVariable int id) {
//        logService.deleteLog(id);
//    }
}