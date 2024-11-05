package Transport;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TransportDeleteAccount {
    private static final String REQUEST_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\requests.txt";

    public void sendDeleteRequest(String email) throws IOException, NoSuchAlgorithmException {
        if (checkPendingRequest(email)) {
            System.out.println("You already have a pending delete request. Please wait for admin approval.");
            // Return to dashboard if there's already a pending request
            new TransportDashboard().dashboard();
            return;
        }

        String rejectionMessage = checkPreviousRequestStatus(email);
        if (rejectionMessage != null) {
            // Notify user of rejection and allow new delete request
            System.out.println("Your previous delete request was not approved for the following reason:");
            System.out.println(rejectionMessage);
            System.out.println("You can now submit a new delete request.");
        }

        // Proceed with creating a new delete request
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REQUEST_FILE, true))) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please provide a reason for account deletion:");
            String reason = scanner.nextLine().trim();

            writer.write(email + ", " + reason + ", pending");
            writer.newLine();

            System.out.println("Your delete request has been sent to the admin for approval.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the delete request.");
        }

        new TransportDashboard().dashboard();
    }

    private boolean checkPendingRequest(String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUEST_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] requestDetails = line.split(", ");
                if (requestDetails.length >= 3 && requestDetails[0].equals(email)) {
                    String status = requestDetails[2];
                    if ("pending".equalsIgnoreCase(status)) {
                        return true;  // Pending request found
                    }
                }
            }
        }
        return false;
    }

    private String checkPreviousRequestStatus(String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUEST_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] requestDetails = line.split(", ");
                if (requestDetails.length >= 4 && requestDetails[0].equals(email)) {
                    String status = requestDetails[2];
                    if ("not approved".equalsIgnoreCase(status)) {
                        return requestDetails[3];  // Return the rejection reason
                    }
                }
            }
        }
        return null;  // No rejection found
    }
}
