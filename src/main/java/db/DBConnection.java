package db;
import com.google.gson.internal.$Gson$Preconditions;
import users.User;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    final String INSERT_STUDENT  =                   "INSERT INTO students_table(codewars_username, counter) VALUES(?, ?)";
    final String SELECT_STUDENT =                    "SELECT * FROM students_table WHERE codewars_username = ? ";
    final String RETRIEVE_ALL_STUDENTS =             "SELECT * FROM users";
    final String RETRIEVE_SINGLE_STUDENT =           "SELECT counter FROM students_table WHERE codewars_username = ? ";
    final String UPDATE_STUDENT_TB=                  "UPDATE students_table SET counter = counter + 1 WHERE codewars_username = ? ";
    final String DELETE_A_STUDENT =                  "DELETE FROM students_table WHERE codewars_username = ? ";
    final String DELETE_ALL_STUDENTS =               "DELETE FROM students_table";
    final String RETRIEVE_ALL_ROWS =                 "SELECT COUNT(*) AS counter FROM students_table";

    PreparedStatement insert_student;
    PreparedStatement select_student;
    PreparedStatement retrieve_all_students;
    PreparedStatement retrieve_single_student;
    PreparedStatement update_student_tb;
    PreparedStatement delete_a_student;
    PreparedStatement delete_all_students;
    PreparedStatement retrieve_all_rows;

    public DBConnection() {
 try{
            String username = "lunga";
            String password = "pg123";
            final String url = "jdbc:postgresql://localhost/students_table?user=lunga&password=pg123&ssl=true";
            Connection connection = DriverManager.getConnection(url, username, password);

            insert_student = connection.prepareStatement(INSERT_STUDENT);
            select_student = connection.prepareStatement(SELECT_STUDENT);
            retrieve_all_students = connection.prepareCall(RETRIEVE_ALL_STUDENTS);
            retrieve_single_student = connection.prepareStatement(RETRIEVE_SINGLE_STUDENT);
            update_student_tb = connection.prepareStatement(UPDATE_STUDENT_TB);
            delete_a_student = connection.prepareStatement(DELETE_A_STUDENT);
            delete_all_students = connection.prepareStatement(DELETE_ALL_STUDENTS);
//            retrieve_all_rows = connectio2n.prepareStatement(RETRIEVE_ALL_ROWS);

            ResultSet resultSet = retrieve_all_students.executeQuery();

            while (resultSet.next()) System.out.println(resultSet.getString("codewarsusername"));

        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e);
        }
    }



    public static void main(String[] args) {
        DBConnection db = new DBConnection();
    }
}
