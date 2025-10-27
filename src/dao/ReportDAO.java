package dao;

import java.sql.SQLException;

public interface ReportDAO {

    void getAllStudents() throws SQLException;
    void getAverageGradePerCourse()  throws SQLException;
    void getCoursesWithTeachers()  throws SQLException;
    void getStudentsAverageGrades()  throws SQLException;
    void getTop3Students()  throws SQLException;
    void getCoursesWithHighestAndLowestGrades()  throws SQLException;

}
