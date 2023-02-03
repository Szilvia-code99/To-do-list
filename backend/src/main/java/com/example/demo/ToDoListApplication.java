package com.example.demo;

import com.example.demo.repository.LogRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.Entity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
@EntityScan(basePackages = {"com.example.demo.model"})
public class ToDoListApplication {

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LogRepository logRepository;

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

}
