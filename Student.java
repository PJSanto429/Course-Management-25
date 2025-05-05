import java.util.ArrayList;
import java.util.Random;

public class Student extends Person {

    private static Utils pri = new Utils();
    private String major;
    private double gpa;
    private int gradYear;
    /*
    * There are a few Courses. Contains name, description, and department
        * There are multiple Classes per Course. Contains Professor id, meeting time/spot, etc
            * Enrollements each have a Class id + Student id. Every Student in a class has an Enrollement
            *   for that Class
     */
    private ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
    
    public Student (
        String id,
        String name,
        String email,
        String username,
        String password,
        String major,
        double gpa,
        int gradYear
    ) {
        super(id, name, email, username, password);
        this.major = major;
        this.gpa = gpa;
        this.gradYear = gradYear;
    }

    public Student (
        Random random,
        String name,
        String email,
        String username,
        String password,
        String major,
        double gpa,
        int gradYear
    ) {
        super(random, name, email, username, password);
        this.major = major;
        this.gpa = gpa;
        this.gradYear = gradYear;
    }

    public String getMajor() {
        return this.major;
    }

    public double getGpa() {
        return this.gpa;
    }

    public int getGradYear() {
        return this.gradYear;
    }

    public ArrayList<Enrollment> getEnrollments() {
        return this.enrollments;
    }

    public void removeEnrollment(String enrollmentId) {
        for (int i = 0; i < this.enrollments.size(); i++) {
            Enrollment enrollment = this.enrollments.get(i);
            if (enrollment.getId().equals(enrollmentId)) {
                this.enrollments.remove(i);
                return;
            }
        }
    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    public void viewEnrollments() {
        pri.ntln("Enrollments for " + this.getName());
        pri.spacer();
        for (Enrollment enrollment : enrollments) {
            Class cls = enrollment.getEnrClass();
            if (cls == null) {
                continue;
            }

            pri.ntln(cls.getCourse().getName() + "(" + cls.getCourse().getDepartment() + ")");
            pri.ntln(cls.getCourse().getDescription());
            pri.ntln(cls.getProfessor().getName());
            pri.ntln(cls.getMeetingSpot() + " - " + cls.getMeetingTime());
            pri.spacer();
        }
    }

    public void viewGrades() {
        pri.ntln("Grades for " + this.getName() + ":");
        for (Enrollment enr : this.getEnrollments()) {
            pri.ntln(enr.getName() + ": " + enr.getGrade() + "%");
        }
    }

    @Override
    public String toString() {
        return "Student" +
            "|id='" + getId() + '\'' +
            "|name='" + getName() + '\'' +
            "|email='" + getEmail() + '\'' +
            "|username='" + getUsername() + '\'' +
            "|password='" + getPassword() + '\'' +
            "|major=" + major +
            "|gpa=" + gpa +
            "|gradYear=" + gradYear;
    }

}
