package UserModule;

import java.util.Scanner;

public class Booking {
    private final Scanner scanner = new Scanner(System.in);

    public void startBooking() {
        System.out.println("Enter Date of Travel (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        System.out.println("Enter Time of Travel (HH:MM): ");
        String time = scanner.nextLine();

        System.out.println("Select Location: ");
        // Example: list of locations
        System.out.println("1. Dhaka");
        System.out.println("2. Chittagong");
        int locationChoice = scanner.nextInt();

        // Display visiting places based on locationChoice
        System.out.println("Available visiting places in chosen location...");
        
        // Code to display buses, seats, etc.
    }
}
