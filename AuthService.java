package service;

import java.util.Scanner;

public class AuthService {

    private final String USERNAME = "admin";
    private final String PASSWORD = "1234";

    public boolean login(Scanner sc) {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter Username: ");
            String user = sc.nextLine();

            System.out.print("Enter Password: ");
            String pass = sc.nextLine();

            if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
                System.out.println("Login successful!\n");
                return true;
            } else {
                attempts--;
                System.out.println("Invalid credentials! Attempts left: " + attempts);
            }
        }

        System.out.println("Too many failed attempts. System locked.");
        return false;
    }
}
