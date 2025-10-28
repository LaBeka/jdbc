package dao;

import models.Student;

import java.sql.SQLException;

public interface StudentDAO {
    int addStudent(Student student) throws SQLException;
    int updateStudentCity(int student_id, String city) throws SQLException;
}
