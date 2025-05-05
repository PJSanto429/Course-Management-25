import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Class {

    Random random = new Random();
    private static Utils pri = new Utils();
    private String id;
    private Course course;
    private Professor professor;
    private String semester;
    private LocalTime meetingTime;
    private String meetingSpot; //? eg. "Woodard 130"

    public Class(
        String id,
        Course course,
        Professor professor,
        String semester,
        LocalTime meetingTime,
        String meetingSpot
    ) {
        this.id = id;
        this.course = course;
        this.professor = professor;
        this.semester = semester;
        this.meetingTime = meetingTime;
        this.meetingSpot = meetingSpot;
    }

    public Class(
        Random random,
        Course course,
        Professor professor,
        String semester,
        LocalTime meetingTime,
        String meetingSpot
    ) {
        this.id = String.valueOf(random.nextInt(1000000, 9999999));
        this.course = course;
        this.professor = professor;
        this.semester = semester;
        this.meetingTime = meetingTime;
        this.meetingSpot = meetingSpot;
    }

    public String getId() {
        return this.id;
    }
    public Class (
        ArrayList<Class> currentClasses,
        ArrayList<Course> availableCourses,
        Professor professor,
        String semester
    ) {
        pri.spacer();
        pri.ntln("Creating a new class");
        pri.spacer();
    
        this.id = String.valueOf(random.nextInt(1000000, 9999999));
    
        if (availableCourses == null || availableCourses.isEmpty()) {
            throw new IllegalArgumentException("No available courses to choose from.");
        }
    
        pri.ntln("Available courses:");
        for (int i = 0; i < availableCourses.size(); i++) {
            Course course = availableCourses.get(i);
            boolean isCourseInCurrentClasses = currentClasses.stream()
                .anyMatch(c -> c.getCourse().equals(course));
            if (isCourseInCurrentClasses) {
                pri.ntln((i + 1) + ". " + course.getName() + " (Already teaching)");
            } else {
                pri.ntln((i + 1) + ". " + course.getName());
            }
        }
    
        pri.nt("Enter the number of the course to assign: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            if (choice < 1 || choice > availableCourses.size()) {
                throw new IllegalArgumentException("Invalid course selection.");
            }
    
            this.course = availableCourses.get(choice - 1);
        } catch (java.util.InputMismatchException e) {
            pri.ntln("Invalid input. Please enter a number.");
            return;
        }
    
        this.professor = professor;
        this.semester = semester;
    
        try {
            pri.nt("Enter the meeting time (HH:MM): ");
            scanner.nextLine();  // consume newline
            String timeInput = scanner.nextLine();
            this.meetingTime = LocalTime.parse(timeInput);
    
            pri.nt("Enter the meeting spot: ");
            this.meetingSpot = scanner.nextLine();
        } catch (java.time.format.DateTimeParseException e) {
            pri.ntln("Invalid time format. Please enter the time in HH:MM format.");
            return;
        }
    
        pri.ntln("Class created successfully!");
    }
    
    public Class editClass() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Editing class details:");
            System.out.print("Enter the new meeting time (HH:MM): ");
            String timeInput = scanner.nextLine();
            LocalTime newMeetingTime = LocalTime.parse(timeInput);

            System.out.print("Enter the new meeting spot: ");
            String newMeetingSpot = scanner.nextLine();

            this.meetingTime = newMeetingTime;
            this.meetingSpot = newMeetingSpot;

            System.out.println("Class details updated successfully!");
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid time format. Please enter the time in HH:MM format.");
        }

        return this;
    }

    public Course getCourse() {
        return this.course;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public String getName() {
        return this.course.getName();
    }

    public String getSemester() {
        return this.semester;
    }

    public LocalTime getMeetingTime() {
        return this.meetingTime;
    }

    public String getMeetingSpot() {
        return this.meetingSpot;
    }

    @Override
    public String toString() {
        return "Class" +
                "|id='" + id + '\'' +
                "|courseId='" + course.getId() + '\'' +
                "|professorId='" + professor.getId() + '\'' +
                "|semester='" + semester + '\'' +
                "|meetingTime='" + meetingTime + '\'' +
                "|meetingSpot='" + meetingSpot + '\'';

    }
}
