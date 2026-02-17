package service;

import model.Student;
import java.io.*;
import java.util.ArrayList;


public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    // Constructor: runs automatically when object is created
    public StudentService() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully!");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public Student searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    public boolean isDuplicate(int id) {
        return searchStudent(id) != null;
    }
    

    public void updateStudent(int id, String name, int age, String course) {
        Student s = searchStudent(id);
        if (s != null) {
            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            saveToFile();
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent(int id) {
        Student s = searchStudent(id);
        if (s != null) {
            students.remove(s);
            saveToFile();
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    // ---------------- FILE LOGIC ----------------

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getCourse());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            students.clear();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String course = parts[3];

                students.add(new Student(id, name, age, course));
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    // 1. Sort students by name
public void viewStudentsSortedByName() {
    if (students.isEmpty()) {
        System.out.println("No students found.");
        return;
    }

    students.stream()
            .sorted((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
            .forEach(System.out::println);
}

// 2. Filter students by course
public void viewStudentsByCourse(String course) {
    boolean found = false;

    for (Student s : students) {
        if (s.getCourse().equalsIgnoreCase(course)) {
            System.out.println(s);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No students found for course: " + course);
    }
}

// 3. Filter students by age range
public void viewStudentsByAgeRange(int min, int max) {
    boolean found = false;

    for (Student s : students) {
        if (s.getAge() >= min && s.getAge() <= max) {
            System.out.println(s);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No students found in this age range.");
    }
}
// 4. Export students to CSV file
public void exportToCSV() {
    if (students.isEmpty()) {
        System.out.println("No students to export.");
        return;
    }

    try (FileWriter writer = new FileWriter("students.csv")) {

        // Header
        writer.append("ID,Name,Age,Course\n");

        // Data
        for (Student s : students) {
            writer.append(s.getId() + ","
                        + s.getName() + ","
                        + s.getAge() + ","
                        + s.getCourse() + "\n");
        }

        System.out.println("Students exported successfully to students.csv");

    } catch (IOException e) {
        System.out.println("Error exporting students: " + e.getMessage());
    }
}


}
