import java.util.ArrayList;
import java.util.Random;

public class Enrollment {
    
    private String id;
    private String studentId;
    private Class enrClass;
    private float grade;
    private ArrayList<Grade> grades = new ArrayList<Grade>();
    
    public Enrollment(
        String id,
        String studentId,
        Class enrClass,
        ArrayList<Grade> grades
    ) {
        this.id = id;
        this.studentId = studentId;
        this.enrClass = enrClass;
        this.grades = grades;
        calculateGrade();
    }

    public Enrollment(
        Random random,
        String studentId,
        Class enrClass
    ) {
        this.id = String.valueOf(random.nextInt(1000000, 9999999));
        this.studentId = studentId;
        this.enrClass = enrClass;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getStudentId() {
        return this.studentId;
    }
    
    public Class getEnrClass() {
        return this.enrClass;
    }

    public String getName() {
        if (this.enrClass.getCourse() == null) {
            return "No course assigned";
        }
        return this.enrClass.getCourse().getName();
    }
    
    public float getGrade() {
        calculateGrade();
        return this.grade;
    }

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    public void calculateGrade() {
        if (grades.size() == 0) {
            this.grade = 100;
            return;
        }

        int total = 0;
        double individualWeight = 100 / grades.size();

        for (Grade g : grades) {
            total += (g.getGrade() * individualWeight);
        }

        this.grade = total / 100;
    }
    
    public void addGrade(Grade grade) {
        grades.add(grade);

        calculateGrade();
    }
    
    @Override
    public String toString() {
        return "Enrollment" +
                "|id='" + id + '\'' +
                "|studentId='" + studentId + '\'' +
                "|classId='" + enrClass.getId() + '\'' +
                "|grade='" + grade + '\'' +
                "|grades=" + grades.stream().map(Grade::getId).toList();
    }
}
