package Utilities_Package.Musers;

import Admin.AdminDashboard;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private String currentEmail = null;

    public void login() {
        Scanner scanner = new Scanner(System.in);
        AdminDashboard showAdminDashboard = new AdminDashboard();  // Create the object for the dashboard

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        // Set the current email
        currentEmail = email;
        System.out.println("Login successful!");

        // Write email to login.txt
        writeEmailToFile(email);

        // After login, show admin dashboard
        showAdminDashboard.displayAdminMenu();
    }

    // Method to write email to login.txt
    private void writeEmailToFile(String email) {
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(loginFile, true))) {  // 'true' for appending to the file
            writer.write(email);
            writer.newLine();
            System.out.println("Email added to login.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to delete email from login.txt
    public void deleteEmailFromFile(String email) {
        File loginFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\Travelogix\\Admin\\AdminFunctionalities\\login.txt");
        List<String> remainingEmails = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(loginFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Add all emails except the one to delete
                if (!line.equalsIgnoreCase(email)) {
                    remainingEmails.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write back the remaining emails
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(loginFile))) {
            for (String remainingEmail : remainingEmails) {
                writer.write(remainingEmail);
                writer.newLine();
            }
            System.out.println("Email removed from login.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String email) {
        this.currentEmail = email;
    }
}
