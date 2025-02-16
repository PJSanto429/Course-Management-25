import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalTime;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Console;

enum Department {
    CompSci,
    Biology
}

public class Main {

    static Random random = new Random();
    static Scanner input = new Scanner(System.in);
    static Console console = System.console();
    static Utils pri = new Utils();

    static String thisSemester = "Spring 2025";
    
    static ArrayList<Course> courses = new ArrayList<Course>();
    static ArrayList<Professor> professors = new ArrayList<Professor>();

    static ArrayList<Class> classes = new ArrayList<Class>();
    static ArrayList<Student> students = new ArrayList<Student>();

    static ArrayList<String> studentOptions = new ArrayList<String>();
    static ArrayList<String> professorOptions = new ArrayList<String>();

    public static Person handleLogIn() {
        Person loggedIn = null;
        while (loggedIn == null) {
            try {
                pri.ntln("Logging in as a Professor(1) or Student(2)? ");
                int userType = Integer.parseInt(input.nextLine());

                if (userType != 1 && userType != 2) {
                    pri.ntln("Bad input. Please enter 1 for Professor or 2 for Student.");
                    continue;

                }

                pri.nt("Enter username: ");
                String username = input.nextLine();

                pri.nt("Enter password: ");
                String password = new String(console.readPassword());

                Person user = Person.logIn(username, password);
                
                if (user != null) {
                    pri.ntln("Login successful!\nWelcome, " + user.getName() + "!\n");
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
                    pri.ntln("Login failed. Please check your username and password and try again.\n\n");
                }

            } catch (Exception e) {
                pri.ntln("Something went wrong logging in. Restarting...\n\n");
            }
        }

        return loggedIn;
    }

    public static String studentOptions() {
        Integer choice = null;
        while (choice == null) {
            pri.ntln("\nWhat would you like to do?");

            for (int i = 0; i < studentOptions.size(); i++) {
                pri.ntln(i + 1 + ") " + studentOptions.get(i));
            }

            int choiceToBe = Integer.parseInt(input.nextLine());

            if (choiceToBe > 0 && choiceToBe <= studentOptions.size()) {
                choice = choiceToBe;
            } else {
                pri.ntln("Invalid input. Please try again.\n");
            }
        }
        return "S" + choice.toString();
    }

    public static String professorOptions() {
        Integer choice = null;
        while (choice == null) {
            pri.ntln("What would you like to do?");

            for (int i = 0; i < professorOptions.size(); i++) {
                pri.ntln(i + 1 + ") " + professorOptions.get(i));
            }

            int choiceToBe = Integer.parseInt(input.nextLine());

            if (choiceToBe > 0 && choiceToBe <= professorOptions.size()) {
                choice = choiceToBe;
            } else {
                pri.ntln("Invalid input. Please try again.\n");
            }
        }

        return "P" + choice.toString();
    }

    public static void mainLoop() {
        Person user = null;

        pri.ntln("Welcome to CourseManager 2025");

        while (user == null) {
            user = handleLogIn();
        }

        boolean running = true;
        while (running) {
            String choice = null;
            while (choice == null) {
                if (user instanceof Student) {
                    choice = studentOptions();
                } else if (user instanceof Professor) {
                    choice = professorOptions();
                }
            }

            //TODO Change the create options method below as well as the options here. This is a shortened list but it makes much more sense than the previous version
            switch (choice) {

                // * Student options

                case "S3":
                    pri.ntln("View Classes/Enrollments is not implemented yet");
                    break;
                case "S6":
                    pri.ntln("View Grades is not implemented yet");
                    break;
                case "S7":
                    pri.ntln("Log Out is not implemented yet");
                    break;

                // * Professor options

                case "P1":
                    pri.ntln("Add/View/Edit Courses is not implemented yet");
                    break;
                case "P2":
                    pri.ntln("Current students is not implemented yet");
                    break;
                case "P3":
                    pri.ntln("Add/View/Edit Grades is not implemented yet");
                    break;
                case "P5":
                    pri.ntln("Log Out is not implemented yet");
                    break;
            
                default:
                    pri.ntln("Invalid input. Please try again.\n");
                    break;
            }
        }
    }

    public static void setData() {
        try {
            FileWriter myWriter = new FileWriter("data.txt");
            myWriter.write("Courses\n");
            for (Course course : courses) {
                myWriter.write(course.toString() + "\n");
            }

            myWriter.write("\nProfessors\n");
            for (Professor professor : professors) {
                myWriter.write(professor.toString() + "\n");
            }

            myWriter.write("\nClasses\n");
            for (Class cls : classes) {
                // TODO: Update class.toString() to include the meeting spot. This should be the last attribute to be displayed
                myWriter.write(cls.toString() + "\n");
            }

            myWriter.write("\nStudents\n");
            for (Student student : students) {
                myWriter.write(student.toString() + "\n");
            }

            myWriter.write("\nEnrollments\n");
            for (Student student : students) {
                for (Enrollment enrollment : student.getEnrollments()) {
                    myWriter.write(enrollment.toString() + "\n");
                }
            }

            myWriter.write("\nGrades\n");
            for (Student student : students) {
                for (Enrollment enrollment : student.getEnrollments()) {
                    for (Grade grade : enrollment.getGrades()) {
                        myWriter.write(grade.toString() + "\n");
                    }
                }
            }

            myWriter.write("\nDONE");

            myWriter.close();
        } catch (IOException err) {
            pri.ntln("Error: " + err);
        }
    }

    public static String getValue(String value) {
        try {
            return value.split("=")[1].replace("'", "");
        } catch (Exception e) {
            return "";
        }
    }

    public static int loadData() {
        File myObj = new File("data.txt");
        ArrayList<Grade> grades = new ArrayList<Grade>();
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

        try {
            if (myObj.createNewFile()) {
                pri.ntln("File created: " + myObj.getName());
                return 1; // File created
            } else {
                Scanner reader = new Scanner(myObj);

                while (reader.hasNextLine()) {
                    String[] data = reader.nextLine().split("\\|");

                    switch (data[0]) {
                        case "Course":
                            courses.add(new Course(
                                getValue(data[1]),
                                getValue(data[2]),
                                getValue(data[3]),
                                Department.valueOf(getValue(data[4]))
                            ));
                            break;

                        case "Professor":
                            professors.add(new Professor(
                                getValue(data[1]),
                                getValue(data[2]),
                                getValue(data[3]),
                                getValue(data[4]),
                                getValue(data[5]),
                                Department.valueOf(getValue(data[6])),
                                getValue(data[7]),
                                getValue(data[8])
                            ));
                            break;
                        case "Class":
                            Course course = null;
                            for (Course c : courses) {
                                if (c.getId().equals(getValue(data[2]))) {
                                    course = c;
                                    break;
                                }
                            }
                            classes.add(new Class(
                                getValue(data[1]),
                                course,
                                getValue(data[3]),
                                getValue(data[4]),
                                LocalTime.parse(getValue(data[5])),
                                getValue(data[6])
                            ));
                            break;
                        case "Student":
                            students.add(new Student(
                                getValue(data[1]),
                                getValue(data[2]),
                                getValue(data[3]),
                                getValue(data[4]),
                                getValue(data[5]),
                                Department.valueOf(getValue(data[6])),
                                Double.parseDouble(getValue(data[7])),
                                Integer.parseInt(getValue(data[8]))
                            ));
                            break;
                        case "Grade":
                            grades.add(new Grade(
                                getValue(data[1]),
                                getValue(data[2]),
                                getValue(data[3]),
                                Double.parseDouble(getValue(data[4]))
                            ));
                            break;
                        case "Enrollment":
                            enrollments.add(new Enrollment(
                                getValue(data[1]),
                                getValue(data[2]),
                                getValue(data[3])
                            ));
                            break;
                        default:
                            break;
                    }
                }
                reader.close();
                return 2; // File already exists
            }
        } catch (IOException err) {
            pri.ntln("Error: " + err);
            return 0; // Error
        }

    }

    public static void createOptions() {
        //! Adding options to the options' list
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
    }

    public static void main(String[] args) {
        createOptions();
        loadData();
        mainLoop();
    }
}