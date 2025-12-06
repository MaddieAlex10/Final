public class Instructor extends Person {
    private String department;
    private int instructorNumber;

    public Instructor() {
        super();
        this.department = "General";
        this.instructorNumber = 0;
    }

    public Instructor(int id, String name, String email, String department, int instructorNumber) {
        super(id, name, email);
        this.department = department;
        this.instructorNumber = instructorNumber;
    }

    @Override
    public String toString() {
        return String.format("Instructor[id=%d, name=%s, dept=%s, instrNo=%d]",
                id, name, department, instructorNumber);
    }
}
