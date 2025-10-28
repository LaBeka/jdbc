package dao;

import models.Course;

import java.sql.SQLException;
import java.util.Optional;

public interface CourseDAO {
    int addCourse(Course course) throws SQLException;
    Optional<Course> getCourseByID(int course_id) throws SQLException;
}
