import java.util.Random;

public class Grade {
    
    private String id;
    private double weight;
    private String assignmentName;
    private String comments;
    private int grade;
    
    public Grade(
        Random random,
        double weight,
        String assignmentName,
        String comments,
        int grade
    ) {
        this.id = String.valueOf(random.nextInt(1000000, 9999999));
        this.weight = weight;
        this.assignmentName = assignmentName;
        this.comments = comments;
        this.grade = grade;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getAssignmentName() {
        return this.assignmentName;
    }

    public String getComments() {
        return this.comments;
    }

    public int getGrade() {
        return this.grade;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "Grade [" +
                "id='" + id + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", comments='" + comments + '\'' +
                ", grade='" + grade + '\'' +
                ']';
    }

}
