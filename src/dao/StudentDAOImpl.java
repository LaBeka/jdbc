package dao;

import connection.DBConnector;
import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO{
    private final DBConnector dbConnector = new DBConnector();

    @Override
    public int addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (student_id, name, age, city) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getStudent_id());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getCity());

            // executeUpdate returns number of affected rows
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // optional: rethrow for higher-level handling
        }
    }

    @Override
    public int updateStudentCity(int student_id, String city) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id = ? AND city = ?;";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student_id);
            pstmt.setString(2, city);

            // executeUpdate returns number of affected rows
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // optional: rethrow for higher-level handling
        }
    }
}
