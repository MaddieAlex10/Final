import java.io.*;
import java.util.*;

public class UniversitySystem {
    // Collections to hold data
    private Map<Integer, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();

    public static void main(String[] args) {
        UniversitySystem sys = new UniversitySystem();
        sys.run();
    }

    private void run() {
        try {
            loadStudentsFromCSV("students.csv"); // file I/O: read students
            loadCoursesFromCSV("courses.csv");   // file I/O: read courses
        } catch (IOException e) {
            System.out.println("Warning: could not load CSVs: " + e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        // Create some objects interactively
        System.out.println("Enter commands: enroll <studentId> <courseCode> or 'list' or 'save' or 'quit'");
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) break;
            if (line.equalsIgnoreCase("list")) {
                displayAll();
                continue;
            }
            if (line.equalsIgnoreCase("save")) {
                try { saveReport("report.txt"); System.out.println("Saved report.txt"); } 
                catch (IOException e) { System.out.println("Save failed: "+e.getMessage()); }
                continue;
            }
            String[] parts = line.split("\\s+");
            if (parts.length == 3 && parts[0].equalsIgnoreCase("enroll")) {
                try {
                    int sid = Integer.parseInt(parts[1]);
                    String code = parts[2];
                    enrollStudent(sid, code);
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid student id.");
                }
            } else {
                System.out.println("Unknown command.");
            }
        }
        sc.close();
    }

    private void enrollStudent(int studentId, String courseCode) {
        Student s = students.get(studentId);
        Course c = courses.get(courseCode);
        if (s == null) { System.out.println("Student not found."); return; }
        if (c == null) { System.out.println("Course not found."); return; }
        boolean ok = c.enroll(s);
        System.out.println(ok ? "Enrolled." : "Could not enroll (full or already enrolled).");
    }

    private void displayAll() {
        System.out.println("Students:");
        for (Student s : students.values()) System.out.println("  " + s);
        System.out.println("Courses:");
        for (Course c : courses.values()) {
            System.out.println("  " + c);
            for (Student s : c.getEnrolled()) System.out.println("     - " + s.getName() + " (id=" + s.getId() + ")");
        }
    }

    private void saveReport(String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Course c : courses.values()) {
                out.println(c);
                for (Student s : c.getEnrolled()) {
                    out.println(String.format("%s,%d,%s", c.toString(), s.getId(), s.getName()));
                }
            }
        }
    }

    private void loadStudentsFromCSV(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                // CSV format: id,name,email,major,studentNumber
                String[] p = line.split(",");
                if (p.length < 5) continue;
                int id = Integer.parseInt(p[0].trim());
                String name = p[1].trim();
                String email = p[2].trim();
                String major = p[3].trim();
                int studNo = Integer.parseInt(p[4].trim());
                students.put(id, new Student(id, name, email, major, studNo));
            }
        }
    }

    private void loadCoursesFromCSV(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                // CSV format: code,title,instructorId,instructorName,instructorEmail,dept,capacity
                String[] p = line.split(",");
                if (p.length < 7) continue;
                String code = p[0].trim();
                String title = p[1].trim();
                int instrId = Integer.parseInt(p[2].trim());
                String instrName = p[3].trim();
                String instrEmail = p[4].trim();
                String dept = p[5].trim();
                int cap = Integer.parseInt(p[6].trim());
                Instructor instr = new Instructor(instrId, instrName, instrEmail, dept, instrId);
                Course c = new Course(code, title, instr, cap);
                courses.put(code, c);
            }
        }
    }
}
