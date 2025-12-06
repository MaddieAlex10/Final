public class Student extends Person {
    private String major;
    private int studentNumber;

    public Student() {
        super();
        this.major = "Undeclared";
        this.studentNumber = 0;
    }

    public Student(int id, String name, String email, String major, int studentNumber) {
        super(id, name, email);
        this.major = major;
        this.studentNumber = studentNumber;
    }

    public String getMajor() { return major; }
    public int getStudentNumber() { return studentNumber; }

    @Override
    public String toString() {
        return String.format("Student[id=%d, name=%s, major=%s, studentNo=%d]",
                id, name, major, studentNumber);
    }
}
