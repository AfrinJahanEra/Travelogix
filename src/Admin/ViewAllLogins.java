package Admin;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ViewAllLogins {

   
    private static final String USERS_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\users.txt";

    private List<String[]> travelers = new ArrayList<>();
    private List<String[]> admins = new ArrayList<>();
    private List<String[]> transports = new ArrayList<>();

    public void showAllLogins() throws NoSuchAlgorithmException, IOException {
        AdminDashboard adminDashboard = new AdminDashboard();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                
                if (userDetails.length >= 4) {
                    String role = userDetails[0].trim();
                    String name = userDetails[1].trim();
                    String phoneNumber = userDetails[2].trim();
                    String email = userDetails[3].trim();

                    // Add user information as an array for structured display
                    String[] userInfo = { name, email, phoneNumber };

                    switch (role.toLowerCase()) {
                        case "traveler":
                            travelers.add(userInfo);
                            break;
                        case "admin":
                            admins.add(userInfo);
                            break;
                        case "transport":
                            transports.add(userInfo);
                            break;
                        default:
                            System.out.println("Unknown role for user: " + name);
                            break;
                    }
                }
            }

            displayCategorizedUsers();

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        adminDashboard.displayAdminMenu();
    }

    private void displayCategorizedUsers() {
        System.out.println("Travelers:");
        System.out.printf("%-15s %-25s %-15s%n", "Name", "Email", "Phone");
        travelers.forEach(user -> System.out.printf("%-15s %-25s %-15s%n", user[0], user[1], user[2]));

        System.out.println("\nAdmins:");
        System.out.printf("%-15s %-25s %-15s%n", "Name", "Email", "Phone");
        admins.forEach(user -> System.out.printf("%-15s %-25s %-15s%n", user[0], user[1], user[2]));

        System.out.println("\nTransports:");
        System.out.printf("%-15s %-25s %-15s%n", "Name", "Email", "Phone");
        transports.forEach(user -> System.out.printf("%-15s %-25s %-15s%n", user[0], user[1], user[2]));
    }
}


