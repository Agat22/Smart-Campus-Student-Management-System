import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // =========================
    // STUDENT
    // =========================
    static class Student {
        String id;
        String name;
        String program;
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Result> results = new ArrayList<>();

        Student(String id, String name, String program) {
            this.id = id;
            this.name = name;
            this.program = program;
        }

        void displayProfile() {
            System.out.println("\n--- STUDENT PROFILE ---");
            System.out.println("Student ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Program: " + program);
        }

        void registerCourse(Course course) {
            courses.add(course);
            System.out.println("Course registered successfully.");
        }

        void viewCourses() {
            System.out.println("\n--- REGISTERED COURSES ---");

            if (courses.isEmpty()) {
                System.out.println("No courses registered.");
                return;
            }

            for (Course course : courses) {
                System.out.println(course.code + " - " + course.name);
            }
        }

        void viewResults() {
            System.out.println("\n--- RESULTS ---");

            if (results.isEmpty()) {
                System.out.println("No results available.");
                return;
            }

            for (Result result : results) {
                System.out.println(
                    result.course.code + " - " +
                    result.course.name +
                    " | Marks: " + result.marks +
                    " | Grade: " + result.grade
                );
            }
        }

        double calculateGPA() {

            if (results.isEmpty()) {
                return 0.0;
            }

            double totalPoints = 0;
            int totalCourses = 0;

            for (Result result : results) {
                totalPoints += result.gradePoint();
                totalCourses++;
            }

            return totalPoints / totalCourses;
        }

        void viewGPA() {
            System.out.printf("\nGPA: %.2f%n", calculateGPA());
        }

        void viewTranscript() {
            System.out.println("\n========== ACADEMIC TRANSCRIPT ==========");
            System.out.println("Student ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Program: " + program);

            viewResults();

            System.out.printf("GPA: %.2f%n", calculateGPA());

            System.out.println("==========================================");
        }
    }


    // =========================
    // COURSE
    // =========================
    static class Course {
        String code;
        String name;
        String lecturer;

        Course(String code, String name, String lecturer) {
            this.code = code;
            this.name = name;
            this.lecturer = lecturer;
        }

        void displayCourse() {
            System.out.println(
                code + " - " + name +
                " | Lecturer: " + lecturer
            );
        }
    }


    // =========================
    // RESULT
    // =========================
    static class Result {
        Course course;
        double marks;
        String grade;

        Result(Course course, double marks) {
            this.course = course;
            this.marks = marks;
            this.grade = calculateGrade(marks);
        }

        String calculateGrade(double marks) {

            if (marks >= 80) {
                return "A";
            } else if (marks >= 70) {
                return "B";
            } else if (marks >= 60) {
                return "C";
            } else if (marks >= 50) {
                return "D";
            } else {
                return "F";
            }
        }

        double gradePoint() {

            switch (grade) {
                case "A":
                    return 5.0;

                case "B":
                    return 4.0;

                case "C":
                    return 3.0;

                case "D":
                    return 2.0;

                default:
                    return 0.0;
            }
        }
    }


    // =========================
    // LECTURER
    // =========================
    static class Lecturer {

        String id;
        String name;

        Lecturer(String id, String name) {
            this.id = id;
            this.name = name;
        }

        void viewCourses(ArrayList<Course> courses) {

            System.out.println("\n--- ASSIGNED COURSES ---");

            for (Course course : courses) {

                if (course.lecturer.equals(name)) {
                    course.displayCourse();
                }
            }
        }

        void enterMarks(Student student, Course course) {

            System.out.print(
                "Enter marks for " +
                student.name + ": "
            );

            double marks = input.nextDouble();

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks.");
                return;
            }

            Result result = new Result(course, marks);

            student.results.add(result);

            System.out.println(
                "Marks saved successfully."
            );

            System.out.println(
                "Grade: " + result.grade
            );
        }
    }


    // =========================
    // ADMINISTRATOR
    // =========================
    static class Administrator {

        void viewStudents(ArrayList<Student> students) {

            System.out.println("\n--- STUDENTS ---");

            if (students.isEmpty()) {
                System.out.println("No students registered.");
                return;
            }

            for (Student student : students) {

                System.out.println(
                    student.id + " - " +
                    student.name + " - " +
                    student.program
                );
            }
        }

        void viewCourses(ArrayList<Course> courses) {

            System.out.println("\n--- COURSES ---");

            for (Course course : courses) {
                course.displayCourse();
            }
        }

        void addStudent(ArrayList<Student> students) {

            input.nextLine();

            System.out.print("Enter student ID: ");
            String id = input.nextLine();

            System.out.print("Enter student name: ");
            String name = input.nextLine();

            System.out.print("Enter program: ");
            String program = input.nextLine();

            students.add(
                new Student(id, name, program)
            );

            System.out.println(
                "Student added successfully."
            );
        }

        void addCourse(ArrayList<Course> courses) {

            input.nextLine();

            System.out.print("Enter course code: ");
            String code = input.nextLine();

            System.out.print("Enter course name: ");
            String name = input.nextLine();

            System.out.print("Enter lecturer name: ");
            String lecturer = input.nextLine();

            courses.add(
                new Course(code, name, lecturer)
            );

            System.out.println(
                "Course added successfully."
            );
        }
    }


    // =========================
    // MAIN SYSTEM
    // =========================

    static ArrayList<Student> students =
        new ArrayList<>();

    static ArrayList<Course> courses =
        new ArrayList<>();

    static ArrayList<Lecturer> lecturers =
        new ArrayList<>();

    static Administrator administrator =
        new Administrator();


    public static void main(String[] args) {

        // Sample data
        Student student =
            new Student(
                "ST001",
                "Agatha",
                "Diploma in Computer Science"
            );

        students.add(student);

        lecturers.add(
            new Lecturer(
                "L001",
                "Mr. John"
            )
        );

        courses.add(
            new Course(
                "CS101",
                "Object Oriented Programming",
                "Mr. John"
            )
        );

        courses.add(
            new Course(
                "DB101",
                "Database Systems",
                "Mr. John"
            )
        );


        // Start system
        login();
    }


    // =========================
    // LOGIN
    // =========================

    static void login() {

        while (true) {

            System.out.println("\n================================");
            System.out.println(" SMART CAMPUS MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Administrator");
            System.out.println("2. Lecturer");
            System.out.println("3. Student");
            System.out.println("4. Exit");

            System.out.print("Choose user type: ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    administratorMenu();
                    break;

                case 2:
                    lecturerMenu();
                    break;

                case 3:
                    studentMenu();
                    break;

                case 4:
                    System.out.println(
                        "Thank you for using Smart Campus."
                    );
                    return;

                default:
                    System.out.println(
                        "Invalid choice."
                    );
            }
        }
    }


    // =========================
    // ADMIN MENU
    // =========================

    static void administratorMenu() {

        while (true) {

            System.out.println("\n--- ADMINISTRATOR DASHBOARD ---");

            System.out.println("1. View Students");
            System.out.println("2. Add Student");
            System.out.println("3. View Courses");
            System.out.println("4. Add Course");
            System.out.println("5. Logout");

            System.out.print("Choose option: ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    administrator.viewStudents(students);
                    break;

                case 2:
                    administrator.addStudent(students);
                    break;

                case 3:
                    administrator.viewCourses(courses);
                    break;

                case 4:
                    administrator.addCourse(courses);
                    break;

                case 5:
                    return;

                default:
                    System.out.println(
                        "Invalid option."
                    );
            }
        }
    }


    // =========================
    // LECTURER MENU
    // =========================

    static void lecturerMenu() {

        Lecturer lecturer = lecturers.get(0);

        while (true) {

            System.out.println("\n--- LECTURER DASHBOARD ---");

            System.out.println("1. View Assigned Courses");
            System.out.println("2. Enter Student Marks");
            System.out.println("3. Logout");

            System.out.print("Choose option: ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    lecturer.viewCourses(courses);
                    break;

                case 2:

                    if (students.isEmpty()) {
                        System.out.println(
                            "No students available."
                        );
                        break;
                    }

                    System.out.println("\nStudents:");

                    for (int i = 0;
                         i < students.size();
                         i++) {

                        System.out.println(
                            (i + 1) + ". " +
                            students.get(i).name
                        );
                    }

                    System.out.print(
                        "Select student: "
                    );

                    int studentChoice =
                        input.nextInt() - 1;

                    if (studentChoice < 0 ||
                        studentChoice >= students.size()) {

                        System.out.println(
                            "Invalid student."
                        );

                        break;
                    }


                    System.out.println("\nCourses:");

                    for (int i = 0;
                         i < courses.size();
                         i++) {

                        System.out.println(
                            (i + 1) + ". " +
                            courses.get(i).name
                        );
                    }

                    System.out.print(
                        "Select course: "
                    );

                    int courseChoice =
                        input.nextInt() - 1;

                    if (courseChoice < 0 ||
                        courseChoice >= courses.size()) {

                        System.out.println(
                            "Invalid course."
                        );

                        break;
                    }


                    lecturer.enterMarks(
                        students.get(studentChoice),
                        courses.get(courseChoice)
                    );

                    break;

                case 3:
                    return;

                default:
                    System.out.println(
                        "Invalid option."
                    );
            }
        }
    }


    // =========================
    // STUDENT MENU
    // =========================

    static void studentMenu() {

        Student student = students.get(0);

        while (true) {

            System.out.println("\n--- STUDENT DASHBOARD ---");

            System.out.println("1. View Profile");
            System.out.println("2. Register Course");
            System.out.println("3. View Registered Courses");
            System.out.println("4. View Results");
            System.out.println("5. View GPA");
            System.out.println("6. View Transcript");
            System.out.println("7. Logout");

            System.out.print("Choose option: ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    student.displayProfile();
                    break;

                case 2:

                    System.out.println("\n--- AVAILABLE COURSES ---");

                    for (int i = 0;
                         i < courses.size();
                         i++) {

                        System.out.println(
                            (i + 1) + ". " +
                            courses.get(i).code +
                            " - " +
                            courses.get(i).name
                        );
                    }

                    System.out.print(
                        "Select course: "
                    );

                    int courseChoice =
                        input.nextInt() - 1;

                    if (courseChoice >= 0 &&
                        courseChoice < courses.size()) {

                        student.registerCourse(
                            courses.get(courseChoice)
                        );

                    } else {

                        System.out.println(
                            "Invalid course."
                        );
                    }

                    break;

                case 3:
                    student.viewCourses();
                    break;

                case 4:
                    student.viewResults();
                    break;

                case 5:
                    student.viewGPA();
                    break;

                case 6:
                    student.viewTranscript();
                    break;

                case 7:
                    return;

                default:
                    System.out.println(
                        "Invalid option."
                    );
            }
        }
    }
}