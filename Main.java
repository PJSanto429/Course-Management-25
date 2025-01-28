import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalTime;

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

    public void test() {
        System.out.println("Hello World");
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
                System.out.println("Logging in as a Professor(1) or Student(2)? ");
                int userType = Integer.parseInt(input.nextLine());

                if (userType != 1 && userType != 2) {
                    System.out.println("Bad input. Please enter 1 for Professor or 2 for Student.");
                    continue;

                }

                System.out.print("Enter username: ");
                String username = input.nextLine();

                System.out.print("Enter password: ");
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
                    System.out.println("Login failed. Please check your username and password and try again.\n\n");
                }

            } catch (Exception e) {
                System.out.println("Something went wrong logging in. Restarting...\n\n");
            }
        }

        return loggedIn;
    }

    public static void optionsTest() {
        String type = "Student"; // * "Professor"

        ArrayList<String> options = new ArrayList<>();
        options.add("View Courses");
        options.add("View Professors");
        options.add("View Classes");
        options.add("View Students");
        options.add("View Enrollments");
        options.add("View Grades");
        options.add("Log Out");
    }

    public static void studentOptions() {
        // * Options for user
        // 1. View Courses
        // 2. View Professors
        // 3. View Classes
        // 4. View Students
        // 5. View Enrollments
        // 6. View Grades
        // 7. Log Out
    }

    public static void mainLoop() {
        Person user = null;

        System.out.println("Welcome to CourseManager 2025");

        while (user == null) {
            user = handleLogIn();

            if (user != null) {
                System.out.println("User class type: " + user.getClass().getSimpleName().equals("Student"));
            }
        }

        // * Options for user
        // 1. View Courses
        // 2. View Professors
        // 3. View Classes
        // 4. View Students
        // 5. View Enrollments
        // 6. View Grades
        // 7. Log Out

        boolean running = true;
        while (running) {
            System.out.println("Please choose an option:");
            System.out.println("1. View Courses");
            System.out.println("2. View Professors");
            System.out.println("3. View Classes");
            System.out.println("4. View Students");
            System.out.println("5. View Enrollments");
            System.out.println("6. View Grades");
            System.out.println("7. Log Out");

            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
            case 1:
                // Code to view courses
                break;
            case 2:
                // Code to view professors
                break;
            case 3:
                // Code to view classes
                break;
            case 4:
                // Code to view students
                break;
            case 5:
                // Code to view enrollments
                break;
            case 6:
                // Code to view grades
                break;
            case 7:
                running = false;
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
            }
        }

    }

    public static void main(String[] args) {

        // * Seed Data
        courses = seedCourses();
        professors = seedProfessors(random);

        classes = seedClasses(random, courses, professors);
        students = seedStudents(random, classes);

        // mainLoop();
        optionsTest();
    }
}