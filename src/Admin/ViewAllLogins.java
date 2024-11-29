package Admin;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ViewAllLogins {

    private static final String USERS_FILE = "src\\TXT_Files\\users.txt";

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
                            System.out.println(" Unknown role for user: " + name);
                            break;
                    }
                }
            }

            displayCategorizedUsers();

        } catch (IOException e) {
            System.out.println("\n An error occurred while reading the file.");
            e.printStackTrace();
        }

        adminDashboard.displayAdminMenu();
    }

    private void displayCategorizedUsers() {
        // Display categorized users
        displayTable("ADMINS", admins);
        displayTable("TRAVELERS", travelers);
        displayTable("TRANSPORTS", transports);
    }

    private void displayTable(String title, List<String[]> users) {
        // Calculate column widths dynamically
        int nameWidth = Math.max("Name".length(), users.stream().mapToInt(u -> u[0].length()).max().orElse(0));
        int emailWidth = Math.max("Email".length(), users.stream().mapToInt(u -> u[1].length()).max().orElse(0));
        int phoneWidth = Math.max("Phone".length(), users.stream().mapToInt(u -> u[2].length()).max().orElse(0));
    
        int totalWidth = nameWidth + emailWidth + phoneWidth + 12; 
    
        // Print the title
        System.out.printf(" %s%n", title.toUpperCase());
    
        // Print top border
        System.out.printf("┌─────┬%s┬%s┬%s┐%n",
            "─".repeat(nameWidth + 2),
            "─".repeat(emailWidth + 2),
            "─".repeat(phoneWidth + 2));
    
        // Print header row
        System.out.printf("│ %-3s │ %-"+nameWidth+"s │ %-"+emailWidth+"s │ %-"+phoneWidth+"s │%n",
            "No.", "Name", "Email", "Phone");
    
        // Print header separator
        System.out.printf("├─────┼%s┼%s┼%s┤%n",
            "─".repeat(nameWidth + 2),
            "─".repeat(emailWidth + 2),
            "─".repeat(phoneWidth + 2));
    
        // Print rows
        if (users.isEmpty()) {
            System.out.printf("│ %-"+(totalWidth - 2)+"s │%n", "No users found.");
        } else {
            for (int i = 0; i < users.size(); i++) {
                String[] user = users.get(i);
                System.out.printf("│ %-3d │ %-"+nameWidth+"s │ %-"+emailWidth+"s │ %-"+phoneWidth+"s │%n",
                    i + 1, user[0], user[1], user[2]);
            }
        }
    
        // Print bottom border
        System.out.printf("└─────┴%s┴%s┴%s┘%n%n",
            "─".repeat(nameWidth + 2),
            "─".repeat(emailWidth + 2),
            "─".repeat(phoneWidth + 2));
    }
}