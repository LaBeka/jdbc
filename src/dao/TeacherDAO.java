package dao;

import models.Course;
import models.Teacher;

import java.sql.SQLException;
import java.util.Optional;

public interface TeacherDAO {
    int addTeacher(Teacher teacher) throws SQLException;
    int assignTeacherToCourse(Teacher teacher, Course course) throws SQLException;
    Optional<Teacher> getTeacherByID(int teacher_id) throws SQLException;
}
