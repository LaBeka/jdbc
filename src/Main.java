import dao.ReportDAOImpl;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ReportDAOImpl pd = new ReportDAOImpl();
        try {
            System.out.println("\n=========Get all students=======");
            pd.getAllStudents();
            System.out.println("\n===========Get Average Grade Per Course===============");
            pd.getAverageGradePerCourse();
            System.out.println("\n=========Get Courses With Highest And Lowest Grades =======");
            pd.getCoursesWithHighestAndLowestGrades();
            System.out.println("\n===============Get Top 3 Students==============");
            pd.getTop3Students();
            System.out.println("\n===============Get Courses With Teachers==============");
            pd.getCoursesWithTeachers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}