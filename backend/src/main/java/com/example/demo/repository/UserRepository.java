package com.example.demo.repository;

import com.example.demo.JdbcHelper;
import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Note;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    public List<UserDTO> getAllUsers() throws ClassNotFoundException {

        Class.forName(JdbcHelper.DRIVER);

        List<UserDTO> users = new ArrayList<UserDTO>();

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement selectStmt = connection.prepareStatement(JdbcHelper.SELECT_USERS_SQL)) {

                ResultSet resultSet = selectStmt.executeQuery();

                while (resultSet.next()) {

                    System.out.println(resultSet.getString(1));
                    UserDTO user = new UserDTO(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("password"));

                    users.add(user);
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

        return users;
    }

    public User getUserById(Integer id) throws ClassNotFoundException {
        Class.forName(JdbcHelper.DRIVER);

        User user = new User() ;

        try (Connection connection = DriverManager.getConnection(JdbcHelper.URL, JdbcHelper.USER, JdbcHelper.PASSWORD)) {

            connection.setAutoCommit(false);

            try (PreparedStatement selectStmt = connection.prepareStatement(JdbcHelper.SELECT_USER_BY_ID)) {

                selectStmt.setInt(1, id);

                ResultSet resultSet = selectStmt.executeQuery();

                while (resultSet.next()) {

                    System.out.println(resultSet.getString(1));
                    user = new User(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("password"));

                }
            } catch (SQLException e) {
                JdbcHelper.printSQLException(e);
                if (connection != null) {
                    try {
                         //
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

        return user;
    }

}