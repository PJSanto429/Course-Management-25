import java.util.ArrayList;
import java.util.Random;

public class Student extends Person {

    private Department major;
    private double gpa;
    private int gradYear;
    private ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
    
    public Student (
        Random random,
        String name,
        String email,
        String username,
        String password,
        Department major,
        double gpa,
        int gradYear
    ) {
        super(random, name, email, username, password);
        this.major = major;
        this.gpa = gpa;
        this.gradYear = gradYear;
    }

    public Department getMajor() {
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

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", major=" + major +
                ", gpa=" + gpa +
                ", gradYear=" + gradYear +
                '}';
    }

}
