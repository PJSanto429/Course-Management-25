import java.time.LocalTime;
import java.util.Random;

public class Class {

    Random random = new Random();

    private String id;
    private Course course;
    private String professorId;
    private String semester;
    private LocalTime meetingTime;

    public Class(
        Random random,
        Course course,
        String professorId,
        String semester,
        LocalTime meetingTime
    ) {
        this.id = String.valueOf(random.nextInt(1000000, 9999999));
        this.course = course;
        this.professorId = professorId;
        this.semester = semester;
        this.meetingTime = meetingTime;
    }

    public String getId() {
        return this.id;
    }

    public Course getCourse() {
        return this.course;
    }

    public String getProfessorId() {
        return this.professorId;
    }

    public String getSemester() {
        return this.semester;
    }

    public LocalTime getMeetingTime() {
        return this.meetingTime;
    }

    @Override
    public String toString() {
        return "Class {" +
                "id='" + id + '\'' +
                ", courseId='" + course.getId() + '\'' +
                ", professorId='" + professorId + '\'' +
                ", semester='" + semester + '\'' +
                ", meetingTime=" + meetingTime +
                '}';
    }
}