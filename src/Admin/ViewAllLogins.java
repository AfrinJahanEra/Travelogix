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

        int totalWidth = nameWidth + emailWidth + phoneWidth + 10; // Add padding for separators and spacing
        String border = "═".repeat(totalWidth);

        // Print table header
        System.out.printf("╔%s╗%n", border);
        System.out.printf("║ %-"+(totalWidth - 2)+"s ║%n", title.toUpperCase());
        System.out.printf("╠%s╣%n", border);
        System.out.printf("║ %-"+nameWidth+"s │ %-"+emailWidth+"s │ %-"+phoneWidth+"s   ║%n", "Name", "Email", "Phone");
        System.out.printf("╠%s╣%n", border);

        // Print user data
        if (users.isEmpty()) {
            System.out.printf("║ %-"+(totalWidth - 2)+"s ║%n", "No users found.");
        } else {
            for (String[] user : users) {
                System.out.printf("║ %-"+nameWidth+"s │ %-"+emailWidth+"s │ %-"+phoneWidth+"s   ║%n", user[0], user[1], user[2]);
            }
        }

        // Print table footer
        System.out.printf("╚%s╝%n%n", border);
    }
}
