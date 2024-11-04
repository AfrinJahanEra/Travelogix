package Transport;

import java.io.*;
import java.util.Scanner;

public class TransportDeleteAccount {
    private static final String REQUEST_FILE = "C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\TXT_Files\\requests.txt";

    public void sendDeleteRequest(String email) {
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
    }
}
