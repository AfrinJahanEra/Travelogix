package Utilities_Package.Musers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    private String currentEmail = null;

    public void login() {
        Scanner scanner = new Scanner(System.in);
        Admin.AdminDashboard showAdminDashboard = new Admin.AdminDashboard();

        while (currentEmail == null) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (isLoginValid(email, password)) {
                currentEmail = email;
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid email or password. Try again.");
            }
        }
    }

    private boolean isLoginValid(String email, String password) {
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(loginFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loginInfo = line.split(",");
                if (loginInfo[0].equalsIgnoreCase(email) && loginInfo[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String email) {
        this.currentEmail = email;
    }
}
