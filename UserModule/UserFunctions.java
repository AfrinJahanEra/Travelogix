package UserModule;

import java.util.Scanner;

public class UserFunctions {
    private final Scanner scanner = new Scanner(System.in);

    public void userDashboard() {
        System.out.println("Welcome to User Dashboard");
        System.out.println("1. Book Ticket");
        System.out.println("2. View Account");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Booking booking = new Booking();
                booking.startBooking();
                break;
            case 2:
                // Show account details
                break;
        }
    }
}
