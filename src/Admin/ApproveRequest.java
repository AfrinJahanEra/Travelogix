package Admin;

import java.io.*;
import java.util.Scanner;

public class ApproveRequest {
    private static final String REQUEST_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\requests.txt";
    private static final String USERS_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\users.txt";

    public void approveTransportAgencyRequests() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUEST_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(REQUEST_FILE + "_temp"))) {

            Scanner scanner = new Scanner(System.in);
            String line;
            boolean hasRequests = false;

            while ((line = reader.readLine()) != null) {
                String[] requestDetails = line.split(", ");
                if (requestDetails.length >= 3) {
                    String email = requestDetails[0];
                    String reason = requestDetails[1];
                    String status = requestDetails[2];

                    if ("pending".equalsIgnoreCase(status)) {
                        hasRequests = true;
                        System.out.println("Request from " + email + " to delete account for reason: " + reason);
                        System.out.println("Approve this request? (1: Approve, 2: Reject)");
                        int choice = scanner.nextInt();

                        if (choice == 1) {
                            deleteUserAccount(email);
                            writer.write(email + ", " + reason + ", approved");
                            System.out.println("Request approved and account deleted.");
                        } else {
                            writer.write(email + ", " + reason + ", not approved");
                            System.out.println("Request not approved.");
                        }
                        writer.newLine();
                    } else {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            if (!hasRequests) {
                System.out.println("No pending delete requests.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while processing requests.");
        }

        replaceFile(REQUEST_FILE + "_temp", REQUEST_FILE);
    }

    private static final String TEMP_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\temp_users.txt";

    private void deleteUserAccount(String email) {
        boolean userDeleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(", ");
                
                // If the email does not match, write the line to the temp file
                if (userDetails.length > 3 && !userDetails[3].equalsIgnoreCase(email)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    // If email matches, mark user as deleted
                    userDeleted = true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the user account.");
        }

        // Replace the original file with the temp file if deletion occurred
        if (userDeleted) {
            File originalFile = new File(USERS_FILE);
            File tempFile = new File(TEMP_FILE);

            if (originalFile.delete()) {
                if (tempFile.renameTo(originalFile)) {
                    System.out.println("User account deleted successfully.");
                } else {
                    System.out.println("Failed to rename the temporary file.");
                }
            } else {
                System.out.println("Failed to delete the original user file.");
            }
        } else {
            System.out.println("User account not found.");
            new File(TEMP_FILE).delete(); // Cleanup if no deletion occurred
        }
    }


    private void replaceFile(String tempFileName, String originalFileName) {
        File tempFile = new File(tempFileName);
        File originalFile = new File(originalFileName);
        
        if (originalFile.delete()) {
            tempFile.renameTo(originalFile);
        }
    }
}


