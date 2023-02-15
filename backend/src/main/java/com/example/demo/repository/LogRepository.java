package com.example.demo.repository;

import com.example.demo.JdbcHelper;
import com.example.demo.dto.LogDTO;
import com.example.demo.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LogRepository {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public List<LogDTO> getAllLogs() throws ClassNotFoundException {
        Class.forName(JdbcHelper.DRIVER);

        List<LogDTO> logs = new ArrayList<LogDTO>();

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement selectStmt = connection.prepareStatement(JdbcHelper.SELECT_LOGS_SQL)) {

                ResultSet resultSet = selectStmt.executeQuery();

                while (resultSet.next()) {

                    LogDTO log = new LogDTO(
                            resultSet.getInt("id"),
                            resultSet.getString("user_id"),
                            resultSet.getString("note_id"),
                            resultSet.getDate("completed_on").toString()
                            );

                    logs.add(log);

                }
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

        return logs;
    }

    public void saveOrUpdate(Log log) throws ClassNotFoundException {
        Class.forName(JdbcHelper.DRIVER);

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement insertStmt = connection.prepareStatement(JdbcHelper.INSERT_LOG_SQL)) {

                // Create insert statement
                insertStmt.setInt(2, Integer.valueOf(log.getNote().getId()));
                insertStmt.setInt(1, Integer.valueOf(log.getUser().getId()));

                java.util.Date date = new java.util.Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
                String currentDateTime = format.format(date);

                insertStmt.setString(3, currentDateTime);
                insertStmt.executeUpdate();

                connection.commit();

            }

            catch (SQLException e) {
                JdbcHelper.printSQLException(e);
                if (connection != null) {
                    try {
                        // Roll back transaction
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

//    public void deleteLog(int id)
//    {
//        logRepository.deleteById(id);
//    }

//    public Optional<Log> getLogById(Integer id) { return logRepository.findById(id); }




}