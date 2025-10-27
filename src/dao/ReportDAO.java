package dao;

import models.Student;

import java.sql.SQLException;
import java.util.List;

public interface ReportDAO {

    List<Student> getAllStudents() throws SQLException;
    void getAverageGradePerCourse()  throws SQLException;
    void getCoursesWithTeachers()  throws SQLException;
    void getStudentsAverageGrades()  throws SQLException;
    void getTop3Students()  throws SQLException;
    void getCoursesWithHighestAndLowestGrades()  throws SQLException;

}
