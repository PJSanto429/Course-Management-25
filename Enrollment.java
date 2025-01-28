import java.util.ArrayList;
import java.util.Random;

public class Enrollment {
    
    private String id;
    private String studentId;
    private String classId;
    private float grade;
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    
    public Enrollment(
        Random random,
        String studentId,
        String classId
    ) {
        this.id = String.valueOf(random.nextInt(1000000, 9999999));
        this.studentId = studentId;
        this.classId = classId;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getStudentId() {
        return this.studentId;
    }
    
    public String getClassId() {
        return this.classId;
    }
    
    public float getGrade() {
        return this.grade;
    }

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);

        int total = 0;

        for (Grade g : grades) {
            total += (g.getGrade() * g.getWeight());
        }

        this.grade = total;
    }
    
    @Override
    public String toString() {
        return "Enrollment [" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", classId='" + classId + '\'' +
                ", grade='" + grade + '\'' +
                ']';
    }
}
