package com.example.demo.service;

import com.example.demo.JdbcHelper;
import com.example.demo.dto.LogDTO;
import com.example.demo.model.Log;
import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.repository.LogRepository;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class LogService {

        @Autowired
        private LogRepository logRepository;

        @Autowired
        private NoteRepository noteRepository;

        @Autowired
        private UserRepository userRepository;

        public List<Log> getAllLogs() { return logRepository.findAll(); }

        public void saveOrUpdate(LogDTO logDTO) throws ClassNotFoundException {
            Class.forName(JdbcHelper.DRIVER);

            try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

                connection.setAutoCommit(false);

                try (PreparedStatement insertStmt = connection.prepareStatement(JdbcHelper.INSERT_LOG_SQL)) {

                    // Create insert statement
                    insertStmt.setInt(2, Integer.valueOf(logDTO.getNoteId()));
                    insertStmt.setInt(1, Integer.valueOf(logDTO.getUserId()));

                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
                    String currentDateTime = format.format(date);
                    insertStmt.setString(3,currentDateTime);
                    insertStmt.executeUpdate();

                    connection.commit();

                }

                catch (SQLException e) {
                    JdbcHelper.printSQLException(e);
                    if (connection != null) {
                        try {
                            // STEP 3 - Roll back transaction
                            System.out.println("Transaction is being rolled back.");
                            connection.rollback();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } catch (SQLException e) {
                JdbcHelper.printSQLException(e);
            }
        }

        public void deleteLog(int id)
        {
            logRepository.deleteById(id);
        }

        public Optional<Log> getLogById(Integer id) { return logRepository.findById(id); }
}
