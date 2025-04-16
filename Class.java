import java.time.LocalTime;
import java.util.Random;

public class Class {

    Random random = new Random();

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
