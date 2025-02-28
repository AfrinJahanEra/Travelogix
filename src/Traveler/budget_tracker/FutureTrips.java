package Traveler.budget_tracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FutureTrips {
    private static final String inputFile = "src/trips.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Scanner scanner = new Scanner(System.in);

    public void viewUpcomingTrips() {
        LocalDate today = LocalDate.now();
        List<String[]> tripList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*,\\s*");
                if (parts.length >= 3) {
                    try {
                        LocalDateTime startDateTime = LocalDateTime.parse(parts[1].trim(), formatter);
                        LocalDateTime endDateTime = LocalDateTime.parse(parts[2].trim(), formatter);

                        if (endDateTime.toLocalDate().isAfter(today)) {
                            tripList.add(parts);
                        }
                    } catch (Exception e) {
                        System.err.println("Error parsing date in line: " + line);
                        e.printStackTrace();
                    }
                }
            }

            // Sort trips by start date
            tripList.sort(Comparator.comparing(t -> LocalDateTime.parse(t[1].trim(), formatter)));

            // Display trips
            System.out.println("════════════════════════════════════════════════════════════════════════");
            System.out.println("║ No. ║ Destination        ║ Start               ║ End                 ║");
            System.out.println("════════════════════════════════════════════════════════════════════════");

            int index = 1;
            for (String[] trip : tripList) {
                System.out.printf("║ %-3d ║ %-18s ║ %-19s ║ %-19s ║\n",
                        index++, trip[0], trip[1], trip[2]);
            }

            System.out.println("════════════════════════════════════════════════════════════════════════");

            if (tripList.isEmpty()) {
                System.out.println("No upcoming trips found.");
                return;
            }

            // Allow user to select a trip for budget management
            if (!tripList.isEmpty())
                selectTrip(tripList);
        } catch (IOException e) {
            System.err.println("File not found or cannot be read.");
            e.printStackTrace();
        }
    }


    private void selectTrip(List<String[]> tripList) {
        System.out.print("Enter the serial number of a trip to manage budget (or 0 to exit): ");

        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                } else if (choice > 0 && choice <= tripList.size()) {
                    String[] selectedTrip = tripList.get(choice - 1);
                    System.out.println("\nManaging Budget for Trip: "+choice);
                    System.out.println("Destination: " + selectedTrip[0] + "\nStart: " + selectedTrip[1] + "\nEnd: " + selectedTrip[2]);


                    // Generate unique budget file for this trip
                    String budgetFileName = "budget_" + selectedTrip[0] + "_" + selectedTrip[1].replace(" ", "_").replace(":", "-") + ".txt";
                    BudgetTracker budgetTracker = new BudgetTracker(budgetFileName);
                    budgetTracker.showBudgetOptions();
                    break;
                } else {
                    System.out.print("Invalid choice. Enter a valid serial number: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
