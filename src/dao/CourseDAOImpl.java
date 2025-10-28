package dao;

import connection.DBConnector;
import models.Course;
import models.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public int addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (course_id, course_name, credits) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, course.getCourse_id());
            pstmt.setString(2, course.getCourse_name());
            pstmt.setInt(3, course.getCredits());

            // executeUpdate returns number of affected rows
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // optional: rethrow for higher-level handling
        }
    }

    @Override
    public Optional<Course> getCourseByID(int course_id) throws SQLException {
        String sql =  "SELECT * FROM courses WHERE course_id = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, course_id);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                return Optional.of(new Course(
                        result.getInt("course_id"),
                        result.getString("course_name"),
                        result.getInt("credits")
                ));
            }
            System.out.println("Course not found");
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
