import java.util.Random;

public class Grade {
    
    private String id;
    private String assignmentName;
    private String comments;
    private Double grade;
    
    public Grade(
        String id,
        String assignmentName,
        String comments,
        Double grade
    ) {
        this.id = id;
        this.assignmentName = assignmentName;
        this.comments = comments;
        this.grade = grade;
    }
    
    public Grade(
        Random random,
        String assignmentName,
        String comments,
        Double grade
    ) {
        this.id = String.valueOf(random.nextInt(1000000, 9999999));
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

    public Double getGrade() {
        return this.grade;
    }

    @Override
    public String toString() {
        return "Grade" +
                "|id='" + id + '\'' +
                "|assignmentName='" + assignmentName + '\'' +
                "|comments='" + comments + '\'' +
                "|grade='" + grade + '\'';
    }

}
