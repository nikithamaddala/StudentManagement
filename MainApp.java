package main; 

import model.Student;
import service.StudentService;
import service.AuthService;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        StudentService service = new StudentService();
        AuthService auth = new AuthService();
        Scanner sc = new Scanner(System.in);

        if (!auth.login(sc)) {
            sc.close();
            return;   
        }


        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("7. View Students Sorted by Name");
            System.out.println("8. View Students by Course");
            System.out.println("9. View Students by Age Range");
            System.out.println("10. Export Students to CSV");



            int choice = getValidInt(sc, "Choose option: ");

            switch (choice) {
                case 1:
                    int id = getValidInt(sc, "Enter ID: ");
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    int age = getValidInt(sc, "Enter Age: ");
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    if (service.isDuplicate(id)) {
                        System.out.println(" Student with this ID already exists!");
                        break;
                    }
                    

                    Student s = new Student(id, name, age, course);
                    service.addStudent(s);
                    break;

                case 2:
                    service.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int sid = getValidInt(sc, "Enter ID to search: ");
                    Student found = service.searchStudent(sid);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ID to update: ");
                    int uid = getValidInt(sc, "Enter ID to update: ");
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Age: ");
                    int newAge = getValidInt(sc, "Enter New Age: ");
                    sc.nextLine();
                    System.out.print("Enter New Course: ");
                    String newCourse = sc.nextLine();

                    service.updateStudent(uid, newName, newAge, newCourse);
                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int did = getValidInt(sc, "Enter ID to delete: ");
                    service.deleteStudent(did);
                    break;

                case 6:
                    System.out.println("Thank you!");
                    sc.close();
                    System.exit(0);

                case 7:
                    service.viewStudentsSortedByName();
                    break;

                case 8:
                    System.out.print("Enter Course: ");
                    String Course = sc.nextLine();
                    service.viewStudentsByCourse(Course);
                    break;

                case 9:
                    int min = getValidInt(sc, "Enter Minimum Age: ");
                    int max = getValidInt(sc, "Enter Maximum Age: ");
                    service.viewStudentsByAgeRange(min, max);
                    break;

                case 10:
                    service.exportToCSV();
                    break;
                                        
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
    private static int getValidInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.next(); 
            }
        }
    }
    
}

