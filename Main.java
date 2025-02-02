import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalTime;
import java.util.Map;
import java.util.HashMap;

enum Department {
    CompSci,
    Biology
}

public class Main {

    static Random random = new Random();
    static Scanner input = new Scanner(System.in);

    static String thisSemester = "Spring 2025";
    
    static ArrayList<Course> courses;
    static ArrayList<Professor> professors;

    static ArrayList<Class> classes;
    static ArrayList<Student> students;

    static ArrayList<String> studentOptions = new ArrayList<String>();
    static ArrayList<String> professorOptions = new ArrayList<String>();
    
    public static ArrayList<Course> seedCourses() {
        ArrayList<Course> coursesToReturn = new ArrayList<Course>();

        Course course1 = new Course("CSE 110", "Intro to Software Engineering", "Learn how to build software applications", Department.CompSci);
        Course course2 = new Course("CSE 111", "Software Engineering 2", "Advanced software applications", Department.CompSci);
        // Course course3 = new Course("CSE 210", "Physics I", "Introductory Physics", Department.CompSci);
        // Course course4 = new Course("CSE 211", "Physics II", "Advanced Physics", Department.CompSci);

        coursesToReturn.add(course1);
        coursesToReturn.add(course2);
        // coursesToReturn.add(course3);
        // coursesToReturn.add(course4);

        return coursesToReturn;
    }

    public static ArrayList<Class> seedClasses(
        Random random,
        ArrayList<Course> courses,
        ArrayList<Professor> professors
    ) {
        ArrayList<Class> classesToReturn = new ArrayList<Class>();

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);

            for (int x = 0; x < 1; x++) {
                classesToReturn.add(new Class(
                    random,
                    course,
                    getRandomProfessorByDepartment(random, professors, course.getDepartment()).getId(),
                    thisSemester,
                    LocalTime.of(random.nextInt(8, 12), 0)
                ));
            }
        }


        return classesToReturn;
    }

    public static ArrayList<Professor> seedProfessors(
        Random random
    ) {
        ArrayList<Professor> professorsToReturn = new ArrayList<Professor>();

        professorsToReturn.add(new Professor(
            random,
            "Sally Fields",
            "sfield@school.edu",
            "sfield",
            "password123",
            Department.CompSci,
            "Doctorate Professor",
            "Building 1, Room 101"
        ));

        professorsToReturn.add(new Professor(
            random,
            "Sam Smith",
            "sSmith@school.edu",
            "ssmith",
            "password123",
            Department.CompSci,
            "Professor",
            "Building 1, Room 305"
        ));

        return professorsToReturn;
    }

    public static Professor getRandomProfessorByDepartment(
        Random random,
        ArrayList<Professor> professors,
        Department department
    ) {
        ArrayList<Professor> filteredProfessors = new ArrayList<Professor>();

        for (Professor professor : professors) {
            if (professor.getDepartment().equals(department)) {
                filteredProfessors.add(professor);
            }
        }

        if (filteredProfessors.size() == 0) {
            return null;
        }

        return filteredProfessors.get(random.nextInt(filteredProfessors.size()));
    }

    public static ArrayList<Student> seedStudents(
        Random random,
        ArrayList<Class> classes
    ) {
        ArrayList<Student> studentsToReturn = new ArrayList<Student>();

        Student newStudent = new Student(
            random,
            "Peyton Santo",
            "psanto@uncc.edu",
            "psanto",
            "mypassword",
            Department.CompSci,
            3.5,
            2025
        );
        
        ArrayList<Class> availClasses = new ArrayList<>();

        for (Class cls : classes) {
            if (cls.getCourse().getDepartment() == newStudent.getMajor()) {
                availClasses.add(cls);
            }
        }

        for (Class cls : availClasses) {
            newStudent.addEnrollment(new Enrollment(
                random,
                newStudent.getId(),
                cls.getId()
            ));
        }

        studentsToReturn.add(newStudent);

        return studentsToReturn;
    }

    public static ArrayList<Grade> seedGrades(
        Random random,
        Enrollment enrollment
    ) {
        ArrayList<Grade> grades = new ArrayList<Grade>();

        Grade grade1 = new Grade(
            random,
            0.25,
            "Assignment 1",
            "Good job!",
            90
        );

        Grade grade2 = new Grade(
            random,
            0.25,
            "Assignment 2",
            "Good job!",
            85
        );

        Grade grade3 = new Grade(
            random,
            0.25,
            "Assignment 3",
            "Good job!",
            95
        );

        Grade grade4 = new Grade(
            random,
            0.25,
            "Assignment 4",
            "Good job!",
            100
        );

        grades.add(grade1);
        grades.add(grade2);
        grades.add(grade3);
        grades.add(grade4);

        return grades;
    }

    public static Person handleLogIn() {
        Person loggedIn = null;

        while (loggedIn == null) {
            try {
                Utils.println("Logging in as a Professor(1) or Student(2)? ");
                int userType = Integer.parseInt(input.nextLine());

                if (userType != 1 && userType != 2) {
                    Utils.println("Bad input. Please enter 1 for Professor or 2 for Student.");
                    continue;

                }

                Utils.print("Enter username: ");
                String username = input.nextLine();

                Utils.print("Enter password: ");
                String password = input.nextLine();

                Person user = Person.logIn(username, password);
                
                if (user != null) {
                    switch (userType) {
                        case 1:
                            for (Professor prof : professors) {
                                if (prof.getId() == user.getId()) {
                                    loggedIn = prof;
                                    break;
                                }
                            }
                            break;
                        case 2:
                            for (Student student : students) {
                                if (student.getId() == user.getId()) {
                                    loggedIn = student;
                                    break;
                                }
                            }
                            break;
                    }
                } else {
                    Utils.println("Login failed. Please check your username and password and try again.\n\n");
                }

            } catch (Exception e) {
                Utils.println("Something went wrong logging in. Restarting...\n\n");
            }
        }

        return loggedIn;
    }

    public static void optionsTest() {
        String type = "Student"; // * "Professor"

        ArrayList<String> options = new ArrayList<>();
        if (type.equals("Student")) {
            int choice = studentOptions();
            Utils.println("You chose: " + choice);
        } else if (type.equals("Professor")) {
            int choice = professorOptions();
        }
    }

    public static int studentOptions() {
        Integer choice = null;
        while (choice == null) {
            Utils.println("What would you like to do?");

            for (int i = 0; i < studentOptions.size(); i++) {
                Utils.println(i + 1 + ") " + studentOptions.get(i));
            }

            int choiceToBe = Integer.parseInt(input.nextLine());

            if (choiceToBe > 0 && choiceToBe <= studentOptions.size()) {
                choice = choiceToBe;
            } else {
                Utils.println("Invalid input. Please try again.\n");
            }
        }
        return choice;
    }

    public static int professorOptions() {
        Integer choice = null;
        while (choice == null) {
            Utils.println("What would you like to do?");

            for (int i = 0; i < professorOptions.size(); i++) {
                Utils.println(i + 1 + ") " + professorOptions.get(i));
            }

            int choiceToBe = Integer.parseInt(input.nextLine());

            if (choiceToBe > 0 && choiceToBe <= professorOptions.size()) {
                choice = choiceToBe;
            } else {
                Utils.println("Invalid input. Please try again.\n");
            }
        }

        return choice;
    }

    public static void mainLoop() {
        Person user = null;

        Utils.println("Welcome to CourseManager 2025");

        while (user == null) {
            user = handleLogIn();
        }

        if (user instanceof Student) {
            studentOptions();
        } else if (user instanceof Professor) {
            professorOptions();
        }
    }

    public static void main(String[] args) {

        // * Seed Data
        courses = seedCourses();
        professors = seedProfessors(random);

        classes = seedClasses(random, courses, professors);
        students = seedStudents(random, classes);

        studentOptions.add("View Courses");
        studentOptions.add("View Professors");
        studentOptions.add("View Classes");
        studentOptions.add("View Students");
        studentOptions.add("View Enrollments");
        studentOptions.add("View Grades");
        studentOptions.add("Log Out");

        professorOptions.add("View Courses");
        professorOptions.add("View Students");
        professorOptions.add("View Grades");
        professorOptions.add("Grade Assignment(s)");
        professorOptions.add("Log Out");

        mainLoop();
        // optionsTest();
    }
}