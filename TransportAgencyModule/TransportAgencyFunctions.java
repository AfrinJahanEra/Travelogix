package TransportAgencyModule;

import java.util.Scanner;

public class TransportAgencyFunctions {
    private final Scanner scanner = new Scanner(System.in);

    public void transportAgencyDashboard() {
        System.out.println("Welcome to Transport Agency Dashboard");
        System.out.println("1. Show Locations and Visiting Places");
        System.out.println("2. View Account");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showLocations();
                break;
            case 2:
                // Show account details
                break;
        }
    }

    private void showLocations() {
        System.out.println("Available Locations:");
        // Read and display from file
    }
}
