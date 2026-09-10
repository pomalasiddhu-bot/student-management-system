import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManagement {
    static class Student {
        String rollNumber, name, course;
        double marks;

        Student(String rollNumber, String name, String course, double marks) {
            this.rollNumber = rollNumber;
            this.name = name;
            this.course = course;
            this.marks = marks;
        }

        void display() {
            System.out.printf("Roll: %s | Name: %s | Course: %s | Marks: %.2f%n",
                    rollNumber, name, course, marks);
        }
    }

    static Map<String, Student> students = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": addStudent(); break;
                case "2": viewStudents(); break;
                case "3": searchStudent(); break;
                case "4": updateStudent(); break;
                case "5": deleteStudent(); break;
                case "6": scanner.close(); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter roll number: ");
        String roll = scanner.nextLine().trim();
        if (students.containsKey(roll)) {
            System.out.println("Student already exists.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter course: ");
        String course = scanner.nextLine().trim();

        try {
            System.out.print("Enter marks: ");
            double marks = Double.parseDouble(scanner.nextLine());
            if (marks < 0 || marks > 100) throw new NumberFormatException();
            students.put(roll, new Student(roll, name, course, marks));
            System.out.println("Student added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Marks must be between 0 and 100.");
        }
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students.values()) student.display();
    }

    static void searchStudent() {
        System.out.print("Enter roll number: ");
        Student student = students.get(scanner.nextLine().trim());
        if (student == null) System.out.println("Student not found.");
        else student.display();
    }

    static void updateStudent() {
        System.out.print("Enter roll number: ");
        Student student = students.get(scanner.nextLine().trim());
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        student.name = scanner.nextLine().trim();
        System.out.print("Enter new course: ");
        student.course = scanner.nextLine().trim();

        try {
            System.out.print("Enter new marks: ");
            double marks = Double.parseDouble(scanner.nextLine());
            if (marks < 0 || marks > 100) throw new NumberFormatException();
            student.marks = marks;
            System.out.println("Student updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks. Previous marks retained.");
        }
    }

    static void deleteStudent() {
        System.out.print("Enter roll number: ");
        if (students.remove(scanner.nextLine().trim()) != null)
            System.out.println("Student deleted successfully.");
        else
            System.out.println("Student not found.");
    }
}
