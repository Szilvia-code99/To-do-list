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

    public List<NoteDTO> getAllNotes() throws ClassNotFoundException, SQLException {

        Class.forName(JdbcHelper.DRIVER);

        List<NoteDTO> notes = new ArrayList<NoteDTO>();

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement selectStmt = connection.prepareStatement(JdbcHelper.SELECT_NOTES_SQL)) {

                ResultSet resultSet = selectStmt.executeQuery();

                while (resultSet.next()) {

                    System.out.println(resultSet.getString(1));
                    NoteDTO note = new NoteDTO(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("text"),
                            resultSet.getString("category"),
                            resultSet.getString("text_color"),
                            resultSet.getString("color"),
                            resultSet.getBoolean("pinned"));

                    notes.add(note);
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

        return notes;
    }

    public void addNote(NoteDTO noteDTO) throws ClassNotFoundException {
        Class.forName(JdbcHelper.DRIVER);

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement insertStmt = connection.prepareStatement(JdbcHelper.INSERT_NOTE_SQL)) {

                insertStmt.setString(1, noteDTO.getTitle());
                insertStmt.setString(2, noteDTO.getText());
                insertStmt.setString(3, noteDTO.getCategory());
                insertStmt.setString(4, noteDTO.getTextColor());
                insertStmt.setString(5, noteDTO.getColor());
                insertStmt.setBoolean(6, noteDTO.getPinned());

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


    public List<NoteDTO> getNotesByCategory(String category) throws ClassNotFoundException {

        Class.forName(JdbcHelper.DRIVER);

        List<NoteDTO> notes = new ArrayList<NoteDTO>();

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement selectStmt = connection.prepareStatement(JdbcHelper.SELECT_NOTES_BY_CATEGORY_SQL)) {

                selectStmt.setString(1, category);

                ResultSet resultSet = selectStmt.executeQuery();

                while (resultSet.next()) {

                    System.out.println(resultSet.getString(1));
                    NoteDTO note = new NoteDTO(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("text"),
                            resultSet.getString("category"),
                            resultSet.getString("text_color"),
                            resultSet.getString("color"),
                            resultSet.getBoolean("pinned"));

                    notes.add(note);
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

        return notes;
    }

    public void updateNote(NoteDTO noteDTO) throws ClassNotFoundException {

        Class.forName(JdbcHelper.DRIVER);

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement updateStmt = connection.prepareStatement(JdbcHelper.UPDATE_NOTE_SQL)) {

                // Create insert statement
                updateStmt.setString(1, noteDTO.getCategory());
                updateStmt.setInt(2, noteDTO.getId());

                updateStmt.executeUpdate();

                connection.commit();
            }

            catch (SQLException e) {
                JdbcHelper.printSQLException(e);
                if (connection != null) {
                    try {
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

    public void deleteNote(int id) throws ClassNotFoundException {

        Class.forName(JdbcHelper.DRIVER);

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement deleteStmt = connection.prepareStatement(JdbcHelper.DELETE_NOTE_SQL)) {

                deleteStmt.setInt(1, id);

                deleteStmt.execute();

                connection.commit();
            }

            catch (SQLException e) {
                JdbcHelper.printSQLException(e);
                if (connection != null) {
                    try {
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

    public Optional<Note> getNoteById(Integer id) { return noteRepository.findById(id); }
}
