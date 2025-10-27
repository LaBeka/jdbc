package dao;

import connection.DBConnector;
import models.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOImpl implements ReportDAO {
    @Override
    public List<Student> getAllStudents()  throws SQLException {

        List<Student> students = new ArrayList<>();


        String query = "SELECT student_id, name, age, city FROM students ORDER BY name ASC";

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("city"));
                students.add(s);
            }
        }
        return students;
    }


    // 2. Average grade per course
    public void getAverageGradePerCourse()  throws SQLException {
        String query = """
            SELECT c.course_name, ROUND(AVG(e.grade), 2) AS avg_grade
            FROM enrollments e
            JOIN courses c ON e.course_id = c.course_id
            GROUP BY c.course_name
            ORDER BY avg_grade DESC;
            """;

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Course\t\tAverage Grade");
            System.out.println("-----------------------------");
            while (rs.next()) {
                System.out.printf("%s\t\t%.2f%n",
                        rs.getString("course_name"),
                        rs.getDouble("avg_grade"));
            }

        }
    }

    // 3. Courses and their teachers
    public void getCoursesWithTeachers()  throws SQLException {
        String query = """
            SELECT c.course_name, t.name AS teacher_name
            FROM courses c
            JOIN course_teachers ct ON c.course_id = ct.course_id
            JOIN teachers t ON ct.teacher_id = t.teacher_id
            ORDER BY c.course_name ASC;
            """;

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Course\t\tTeacher");
            System.out.println("-----------------------------");
            while (rs.next()) {
                System.out.printf("%s\t\t%s%n",
                        rs.getString("course_name"),
                        rs.getString("teacher_name"));
            }
        }
    }

    // 4. Students' average grades (including those without grades)
    public void getStudentsAverageGrades()  throws SQLException {
        String query = """
            SELECT s.name,
                   ROUND(AVG(e.grade), 2) AS avg_grade
            FROM students s
            LEFT JOIN enrollments e ON s.student_id = e.student_id
            GROUP BY s.name
            ORDER BY s.name;
            """;

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Student\t\tAverage Grade");
            System.out.println("-----------------------------");
            while (rs.next()) {
                String avg = rs.getString("avg_grade");
                if (avg == null) avg = "No grades";
                System.out.printf("%s\t\t%s%n",
                        rs.getString("name"),
                        avg);
            }

        }
    }

    // 5. Top 3 students by average grade
    public void getTop3Students()  throws SQLException {
        String query = """
            SELECT s.name,
                   ROUND(AVG(e.grade), 2) AS avg_grade
            FROM students s
            JOIN enrollments e ON s.student_id = e.student_id
            GROUP BY s.name
            ORDER BY avg_grade DESC
            LIMIT 3;
            """;

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Top 3 Students");
            System.out.println("-----------------------------");
            while (rs.next()) {
                System.out.printf("%s\t\t%.2f%n",
                        rs.getString("name"),
                        rs.getDouble("avg_grade"));
            }

        }
    }

    // 6. Courses with highest and lowest grades
    public void getCoursesWithHighestAndLowestGrades()  throws SQLException {
        String query = """
            SELECT c.course_name,
                   MAX(e.grade) AS highest,
                   MIN(e.grade) AS lowest
            FROM courses c
            JOIN enrollments e ON c.course_id = e.course_id
            GROUP BY c.course_name
            ORDER BY c.course_name;
            """;

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Course\t\tHighest\tLowest");
            System.out.println("--------------------------------");
            while (rs.next()) {
                System.out.printf("%s\t\t%.1f\t%.1f%n",
                        rs.getString("course_name"),
                        rs.getDouble("highest"),
                        rs.getDouble("lowest"));
            }

        }
    }

    @Override
    public Student getOldestStudent() throws SQLException {
        String query = """
            SELECT *
            FROM students  order by age desc limit 1
            """;
        Student s = null;
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                s = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("city"));
            }
        }
        return s;
    }
}
