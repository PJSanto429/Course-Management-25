public class Course {
    
    private String id;
    private String name;
    private String description;
    private String department;

    public Course(
        String id,
        String name,
        String description,
        String department
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.department = department;
    }
    
    public String getDepartment() {
        return this.department.toString();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "Course" +
                "|id='" + id + '\'' +
                "|name='" + name + '\'' +
                "|description='" + description + '\'' +
                "|department='" + department + '\'';
    }

}
