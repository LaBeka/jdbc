package dao;

import connection.DBConnector;
import models.Course;
import models.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TeacherDAOImpl implements TeacherDAO {

    CourseDAOImpl courseDAO = new CourseDAOImpl();
    @Override
    public int addTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teachers (teacher_id, name, department) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teacher.getTeacher_id());
            pstmt.setString(2, teacher.getName());
            pstmt.setString(3, teacher.getDepartment());

            // executeUpdate returns number of affected rows
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // optional: rethrow for higher-level handling
        }
    }

    @Override
    public int assignTeacherToCourse(Teacher teacher, Course course) throws SQLException {

        Teacher foundTeacher = getTeacherByID(teacher.getTeacher_id()).get();
        Course courseFound = courseDAO.getCourseByID(course.getCourse_id()).get();
        if(foundTeacher == null || courseFound == null) {
            System.out.println("Course or Teacher by provided id not found");
            return -1;
        }

        String sql = "INSERT INTO course_teachers (course_id, teacher_id) VALUES (?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teacher.getTeacher_id());
            pstmt.setInt(2, course.getCourse_id());
            // executeUpdate returns number of affected rows
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // optional: rethrow for higher-level handling
        }
    }

    @Override
    public Optional<Teacher> getTeacherByID(int teacher_id) throws SQLException {
        String sql =  "SELECT * FROM teachers WHERE teacher_id = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, teacher_id);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                return Optional.of(new Teacher(
                        result.getInt("teacher_id"),
                        result.getString("name"),
                        result.getString("department")
                ));
            }
            System.out.println("Teacher not found");
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
