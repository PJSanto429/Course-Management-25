import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Professor extends Person {
    private static Scanner input = new Scanner(System.in);
    private static Utils pri = new Utils();

    private String department;
    private String title;
    private String office;

    public Professor(
        String id,
        String name,
        String email,
        String username,
        String password,
        String department,
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
        String department,
        String title,
        String office
    ) {
        super(random, name, email, username, password);
        this.department = department;
        this.title = title;
        this.office = office;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getTitle() {
        return this.title;
    }

    public String getOffice() {
        return this.office;
    }

    public ArrayList<Class> getClasses(
        ArrayList<Class> allClasses
    ) {
        ArrayList<Class> professorClasses = new ArrayList<Class>();
        for (int i = 0; i < allClasses.size(); i++) {
            Class oneClass = allClasses.get(i);
            if (oneClass.getProfessor().getId().equals(this.getId())) {
                professorClasses.add(oneClass);
            }
        }
        return professorClasses;
    }

    public void addGrades(
        ArrayList<Class> allClasses,
        ArrayList<Student> allStudents
    ) {

        ArrayList<Student> students = displayStudents(allStudents, allClasses, true);

        pri.spacer();
        pri.ntln("Select a student to add grades for:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ") " + students.get(i).getName() + " (" + students.get(i).getId() + ")");
            // pri.ntln((i + 1) + ") " + students.get(i).getName() + " (" + students.get(i).getId() + ")");
        }
        pri.spacer();
        int studentIndex = Integer.parseInt(input.nextLine()) - 1;
        if (studentIndex < 0 || studentIndex >= students.size()) {
            pri.ntln("Invalid selection. Returning to menu.");
            return;
        }

        pri.ntln("Select a course to grade for:");
        ArrayList<Class> professorClasses = getClasses(allClasses);
        for (int i = 0; i < professorClasses.size(); i++) {
            // pri.ntln((i + 1) + ") " + professorClasses.get(i).getCourse().getName());
            System.out.println((i + 1) + ") " + professorClasses.get(i).getCourse().getName());
        }
        int courseIndex = Integer.parseInt(input.nextLine()) - 1;
        if (courseIndex < 0 || courseIndex >= professorClasses.size()) {
            pri.ntln("Invalid selection. Returning to menu.");
            return;
        }

        Class selectedClass = professorClasses.get(courseIndex);
        Student selectedStudent = students.get(studentIndex);

        Enrollment selectedEnrollment = null;
        for (Enrollment enrollment : selectedStudent.getEnrollments()) {
            if (enrollment.getEnrClass().getId().equals(selectedClass.getId())) {
            selectedEnrollment = enrollment;
            break;
            }
        }

        pri.ntln("Enter assignment name:");
        String assignmentName = input.nextLine();

        pri.ntln("Enter comments:");
        String comments = input.nextLine();

        pri.ntln("Enter grade:");
        Double grade;
        try {
            grade = Double.parseDouble(input.nextLine());
        } catch (NumberFormatException e) {
            pri.ntln("Invalid grade entered. Returning to menu.");
            return;
        }

        Random random = new Random();
        selectedEnrollment.addGrade(new Grade(random, assignmentName, comments, grade));
        pri.ntln("Grade added successfully.");

    }

    public ArrayList<Class> manageClasses(
        ArrayList<Class> allClasses,
        ArrayList<Course> availableCourses
    ) {
        pri.ntln("Classes for " + this.getName());
        pri.spacer();
        ArrayList<Class> professorClasses = getClasses(allClasses);
        for (int i = 0; i < professorClasses.size(); i++) {
            Class oneClass = professorClasses.get(i);
            pri.nt("\n" + (i + 1) + ") ");
            pri.ntln("Course: " + oneClass.getCourse().getName());
            pri.ntln("Semester: " + oneClass.getSemester());
            pri.ntln("Meeting Time: " + oneClass.getMeetingTime());
            pri.ntln("Meeting Spot: " + oneClass.getMeetingSpot());
        }
        if (professorClasses.size() == 0) {
            pri.ntln("No classes found for " + this.getName());
        }
        pri.spacer();
        pri.ntln("Make a decision: 0) Exit  1) Add Class  2) Edit Class  3) Remove Class");
        int decision = Integer.parseInt(input.nextLine());
        switch (decision) {
            case 0:
                pri.ntln("Exiting class management.");
                return allClasses;
            case 1:
                Class newClass = new Class(professorClasses, availableCourses, this, "Fall 2023");
                allClasses.add(newClass);
                return allClasses;
            case 2:
                pri.ntln("Select the class to edit:");
                for (int i = 0; i < professorClasses.size(); i++) {
                    pri.ntln((i + 1) + ") " + professorClasses.get(i).getCourse().getName());
                }
                int classToEditIndex = Integer.parseInt(input.nextLine()) - 1;
                if (classToEditIndex >= 0 && classToEditIndex < professorClasses.size()) {
                    Class classToEdit = professorClasses.get(classToEditIndex);
                    pri.ntln("Editing class: " + classToEdit.toString());
                    Class edited = classToEdit.editClass();
                    professorClasses.set(classToEditIndex, edited);
                    pri.ntln("Class updated successfully.");

                    for (int i = 0; i < allClasses.size(); i++) {
                        if (allClasses.get(i).getId().equals(edited.getId())) {
                            allClasses.set(i, edited);
                            break;
                        }
                    }
                    return allClasses;
                } else {
                    pri.ntln("Invalid selection. Returning to menu.");
                }
                break;
            case 3:
                pri.ntln("Select the class to remove:");
                for (int i = 0; i < professorClasses.size(); i++) {
                    pri.ntln((i + 1) + ") " + professorClasses.get(i).getCourse().getName());
                }
                int classToRemoveIndex = Integer.parseInt(input.nextLine()) - 1;
                if (classToRemoveIndex >= 0 && classToRemoveIndex < professorClasses.size()) {
                    Class classToRemove = professorClasses.get(classToRemoveIndex);
                    allClasses.remove(classToRemove);
                    pri.ntln("Class removed successfully.");
                    return allClasses;
                } else {
                    pri.ntln("Invalid selection. Returning to menu.");
                }
                break;
            default:
                pri.ntln("Invalid choice. Please try again.");
        }
        return null;
    }

    
    public ArrayList<Student> displayStudents(
        ArrayList<Student> allStudents,
        ArrayList<Class> allClasses,
        boolean returnStudents
    ) {
        ArrayList<Class> professorClasses = getClasses(allClasses);
        ArrayList<String> classIds = new ArrayList<String>();
        for (int i = 0; i < professorClasses.size(); i++) {
            classIds.add(professorClasses.get(i).getId());
        }

        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<String> studentIds = new ArrayList<String>();

        for (int i = 0; i < allStudents.size(); i++) {
            ArrayList<Enrollment> studentEnrollments = allStudents.get(i).getEnrollments();
            for (Enrollment enrollment : studentEnrollments) {
                if (
                    classIds.contains(enrollment.getEnrClass().getId()) &&
                    !studentIds.contains(allStudents.get(i).getId())
                ) {
                    students.add(allStudents.get(i));
                    studentIds.add(allStudents.get(i).getId());
                }
            }
        }
        if (returnStudents) {
            return students;
        }

        pri.ntln(students.size() + " Current Students:");
        pri.spacer();
        for (int i = 0; i < students.size(); i++) {
            pri.ntln((i + 1) + ") " + students. get(i).getName() + " (" + students.get(i).getId() + ")");
        }
        pri.spacer();
        return null;
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
