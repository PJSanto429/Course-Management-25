import java.util.ArrayList;
import java.util.List;
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
                pri.spacer();
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

    public static Boolean studentOptions(
        Student student
    ) {
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
                pri.invalidInput();
            }
        }

        switch (choice) {
            case 1:
                student.viewEnrollments();
                return true;
            case 2:
                student.viewGrades();
                return true;
            case 3:
                pri.ntln("Logging out.... Please Wait....");
                pri.ntln("Log out successful.");
                return false;
            default:
                pri.invalidInput();
                return true;
        }
    }

    public static Boolean professorOptions(
        Professor professor
    ) {
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
                pri.invalidInput();
            }

            switch (choice) {
                case 1:
                    classes = professor.manageClasses(classes, courses);
                    ArrayList<String> classIds = new ArrayList<String>();
                    for (Class cls : classes) {
                        classIds.add(cls.getId());
                    }

                    for (Student student : students) {
                        ArrayList<Enrollment> studentEnrollments = student.getEnrollments();
                        List<Enrollment> toRemove = new ArrayList<>();

                        for (Enrollment enrollment : studentEnrollments) {
                            if (!classIds.contains(enrollment.getEnrClass().getId())) {
                                toRemove.add(enrollment);
                            }
                        }

                        for (Enrollment enrollment : toRemove) {
                            student.removeEnrollment(enrollment.getId());
                        }
                    }

                    return true;
                case 2:
                    professor.displayStudents(students, classes, false);
                    return true;
                case 3:
                    professor.addGrades(classes, students);
                    return true;
                case 4:
                    pri.ntln("Logging out.... Please Wait....");
                    pri.ntln("Log out successful.");
                    return false;
                default:
                    pri.invalidInput();
                    return true;
            }
        }

        return true;
    }

    public static void mainLoop() {
        
        pri.ntln("Welcome to CourseManager 2025");
        
        boolean running = true;
        Person user = null;//professors.get(0);
        while (running) {

            while (user == null && running == true) {

                Integer choice = null;
                while (choice == null) {

                    pri.nt("Login(1) or Terminate System(2): ");
                    choice = Integer.parseInt(input.nextLine());
                    switch (choice) {
                        case 1:
                            user = handleLogIn();
                            break;
                        case 2:
                            pri.ntln("Terminating System.... Please Wait....");
                            pri.ntln("System successfully terminated.");
                            running = false;
                            break;
                        default:
                            choice = null;
                            pri.invalidInput();
                            break;
                    }
                }
            }
            
            Boolean loggedIn = false;
            if (user instanceof Student) {
                loggedIn = studentOptions((Student) user);
            } else if (user instanceof Professor) {
                loggedIn = professorOptions((Professor) user);
            }
            if (!loggedIn) {
                user = null;
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
                myWriter.write(cls.toString() + "\n");
            }

            myWriter.write("\nStudents\n");
            for (Student student : students) {
                myWriter.write(student.toString() + "\n");
            }

            myWriter.write("\nGrades\n");
            for (Student student : students) {
                for (Enrollment enrollment : student.getEnrollments()) {
                    for (Grade grade : enrollment.getGrades()) {
                        myWriter.write(grade.toString() + "\n");
                    }
                }
            }

            myWriter.write("\nEnrollments\n");
            for (Student student : students) {
                for (Enrollment enrollment : student.getEnrollments()) {
                    myWriter.write(enrollment.toString() + "\n");
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
                                getValue(data[4])
                            ));
                            break;

                        case "Professor":
                            professors.add(new Professor(
                                getValue(data[1]),
                                getValue(data[2]),
                                getValue(data[3]),
                                getValue(data[4]),
                                getValue(data[5]),
                                getValue(data[6]),
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

                            Professor professor = null;
                            for (Professor prof : professors) {
                                if (prof.getId().equals(getValue(data[3]))) {
                                    professor = prof;
                                }
                            }
                            classes.add(new Class(
                                getValue(data[1]),
                                course,
                                professor,
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
                                getValue(data[6]),
                                Double.parseDouble(getValue(data[7])),
                                Integer.parseInt(getValue(data[8]))
                            ));
                            break;
                        case "Enrollment":
                            Class enrClass = null;
                            for (Class cls : classes) {
                                if (cls.getId().equals(getValue(data[3]))) {
                                    enrClass = cls;
                                    break;
                                }
                            }
                            data[5] = getValue(data[5]).replace("[", "").replace("]", "").replace("\"", "");
                            String[] test = data[5].split(",");

                            ArrayList<Grade> enrGrades = new ArrayList<Grade>();
                            if (test.length > 1) {
                                for (String str : test) {
                                    for (Grade g : grades) {
                                        if (g.getId().equals(str)) {
                                            enrGrades.add(g);
                                        }
                                    }
                                }
                            }

                            enrollments.add(new Enrollment(
                                getValue(data[1]),
                                getValue(data[2]),
                                enrClass,
                                enrGrades
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
                        default:
                            break;
                    }
                }
                reader.close();
                for (Student student : students) {
                    for (Enrollment enrl : enrollments) {
                        if (enrl.getStudentId().equals(student.getId())) {
                            student.addEnrollment(enrl);
                        }
                    }
                }
                return 2; // File already exists
            }
        } catch (IOException err) {
            pri.ntln("Error: " + err);
            return 0; // Error
        }
    }

    public static void createOptions() {
        //! Adding options to the options' list
        studentOptions.add("View Classes/Enrollments");
        studentOptions.add("View Grades");
        studentOptions.add("Log Out");

        professorOptions.add("Add/View/Edit Classes");
        professorOptions.add("View Current Students");
        professorOptions.add("Add Grades");
        professorOptions.add("Log Out");
    }

    public static void main(String[] args) {
        createOptions();
        loadData();
        mainLoop();

        setData();
    }
}