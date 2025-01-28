import java.util.Random;

public class Professor extends Person {

    private Department department;
    private String title;
    private String office;

    public Professor(
        Random random,
        String name,
        String email,
        String username,
        String password,
        Department department,
        String title,
        String office
    ) {
        super(random, name, email, username, password);
        this.department = department;
        this.title = title;
        this.office = office;
    }

    public Department getDepartment() {
        return this.department;
    }

    public String getTitle() {
        return this.title;
    }

    public String getOffice() {
        return this.office;
    }
    
}
