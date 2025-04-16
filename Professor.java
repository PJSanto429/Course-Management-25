import java.util.ArrayList;
import java.util.Random;

public class Professor extends Person {
    private static Utils pri = new Utils();

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

    public void manageClasses(
        ArrayList<Class> allClasses
    ) {
        pri.ntln("Classes for " + this.getName());
        pri.spacer();
        for (Class oneClass : allClasses) {
            // pri.ntln("this id " + oneClass.getProfessor().getId().equals(this.getId()));
            // pri ntln("one class professor ==> " + oneClass.getProfessor().getId());
            if (oneClass.getProfessor().getId().equals(this.getId())) {
                pri.ntln("Schedule Details:");
                pri.ntln("ID: " + getId());
                pri.ntln("Course: " + oneClass.getCourse().getName());
                pri.ntln("Professor: " + oneClass.getProfessor().getName());
                pri.ntln("Semester: " + oneClass.getSemester());
                pri.ntln("Meeting Time: " + oneClass.getMeetingTime());
                pri.ntln("Meeting Spot: " + oneClass.getMeetingSpot());
            }
        }
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
