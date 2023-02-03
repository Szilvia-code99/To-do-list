package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.SQLException;

@Getter
@Setter
@ToString
public class JdbcHelper {

    public static final String URL =
            "jdbc:mysql://localhost:3306/to_do_list";
    public static final String USER = "root";
    public static final String PASSWORD = "1234";
    public static final String DRIVER =
            "com.mysql.cj.jdbc.Driver";

    public static final String INSERT_NOTE_SQL = "INSERT INTO note " +
            "  (title, text, category, text_color,color,pinned) VALUES " + " ( ?, ?, ?, ?, ?,?);";

    public static final String INSERT_LOG_SQL = "INSERT INTO log " +
            "  (user_id,note_id,completed_on) VALUES " + " ( ?, ?, ?);";
    public static final String UPDATE_NOTE_SQL = "UPDATE note set category = ? where id = ?;";

    public static final String DELETE_NOTE_SQL = "DELETE FROM note where id = ?;";

    public static final String SELECT_NOTES_SQL = "SELECT id, category, color, text_color,title,pinned,text FROM " + "note";

    public static final String SELECT_NOTES_BY_CATEGORY_SQL = "SELECT id, category, color, text_color,title,pinned,text FROM " + "note where category = ?";

    public static final String SELECT_USERS_SQL = "SELECT * FROM " + "user";

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
