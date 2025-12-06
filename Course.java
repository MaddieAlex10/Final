import java.util.*;

public class Course {
    private String code;
    private String title;
    private Instructor instructor; // composition: Course has an Instructor
    private int capacity;
    private List<Student> enrolled;

    public Course() {
        this("UNK000", "Untitled", new Instructor(), 30);
    }

    public Course(String code, String title, Instructor instructor, int capacity) {
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        this.capacity = capacity;
        this.enrolled = new ArrayList<>();
    }

    public boolean enroll(Student s) {
        if (enrolled.size() >= capacity) return false;
        if (enrolled.contains(s)) return false;
        enrolled.add(s);
        return true;
    }

    public boolean drop(Student s) {
        return enrolled.remove(s);
    }

    public List<Student> getEnrolled() { return Collections.unmodifiableList(enrolled); }

    @Override
    public String toString() {
        return String.format("Course[%s - %s, instr=%s, cap=%d, enrolled=%d]",
                code, title, instructor.getName(), capacity, enrolled.size());
    }
}
