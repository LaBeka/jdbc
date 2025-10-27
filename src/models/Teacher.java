package models;

public class Teacher {

    private int teacher_id;
    private String name;
    private String department;

    public Teacher(int teacher_id, String name, String department) {
        this.teacher_id = teacher_id;
        this.name = name;
        this.department = department;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
