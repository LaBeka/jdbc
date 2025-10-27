package models;

public class Student {
    private int student_id;
    private String name;
    private int age;
    private String city;

    public Student(int student_id, String name, int age, String city) {
        this.student_id = student_id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}
