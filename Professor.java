import java.util.Random;

public class Professor extends Person {

    private Department department;
    private String title;
    private String office;

    public Professor(
        String id,
        String name,
        String email,
        String username,
        String password,
        Department department,
        String title,
        String office
    ) {
        super(id, name, email, username, password);
        this.department = department;
        this.title = title;
        this.office = office;
    }

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

    @Override
    public String toString() {
        return "Professor" +
                "|id='" + getId() + '\'' +
                "|name='" + getName() + '\'' +
                "|email='" + getEmail() + '\'' +
                "|username='" + getUsername() + '\'' +
                "|password='" + getPassword() + '\'' +
                "|department=" + department +
                "|title='" + title + '\'' +
                "|office='" + office + '\'';
    }
    
}
