package Traveler.budget_tracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FutureTrips {
    private static final String inputFile = "src\\TXT_Files\\trips.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Scanner scanner = new Scanner(System.in);

    public void viewUpcomingTrips() {
        LocalDate today = LocalDate.now();
        List<String[]> tripList = new ArrayList<>();

        
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy hh:mm a");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s*,\\s*");

                
                if (parts.length != 3) {
                    continue;  
                }

                try {
                    LocalDateTime startDateTime = LocalDateTime.parse(parts[1].trim(), formatter);
                    LocalDateTime endDateTime = LocalDateTime.parse(parts[2].trim(), formatter);

                    if (endDateTime.toLocalDate().isAfter(today)) {
                        tripList.add(parts);
                    }
                } catch (Exception e) {
                    
                }
            }

            if (tripList.isEmpty()) {
                System.out.println("No upcoming trips found.");
                return;
            }

            
            try {
                tripList.sort(Comparator.comparing(t -> LocalDateTime.parse(t[1].trim(), formatter)));
            } catch (Exception e) {
                System.err.println("Error sorting trips. Some data might be corrupted.");
            }

            
            System.out.println("═════════════════════════════════════════════════════════════════════════════════");
            System.out.println("║ No. ║ Destination          ║ Start                   ║ End                     ║");
            System.out.println("═════════════════════════════════════════════════════════════════════════════════");

            int index = 1;
            for (String[] trip : tripList) {
                
                LocalDateTime startDateTime = LocalDateTime.parse(trip[1].trim(), formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(trip[2].trim(), formatter);
                String formattedStart = startDateTime.format(outputFormatter);
                String formattedEnd = endDateTime.format(outputFormatter);

                
                System.out.printf("║ %-3d ║ %-20s ║ %-23s ║ %-23s ║\n",
                        index++, trip[0], formattedStart, formattedEnd);
            }

            System.out.println("═════════════════════════════════════════════════════════════════════════════════");

            selectTrip(tripList);
        } catch (IOException e) {
            System.err.println("Error: Unable to read the file '" + inputFile + "'. Please check if the file exists and is accessible.");
        }
    }

    private void selectTrip(List<String[]> tripList) {
        System.out.print("Enter the serial number of a trip to manage budget (or 0 to exit): ");

        while (true) {
            try {
                if (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Please enter a valid number: ");
                    scanner.next(); 
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                } else if (choice > 0 && choice <= tripList.size()) {
                    String[] selectedTrip = tripList.get(choice - 1);
                    System.out.println("\nManaging Budget for Trip: " + choice);
                    System.out.println("Destination: " + selectedTrip[0]);

                    
                    LocalDateTime startDateTime = LocalDateTime.parse(selectedTrip[1].trim(), formatter);
                    LocalDateTime endDateTime = LocalDateTime.parse(selectedTrip[2].trim(), formatter);

                    
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy hh:mm a");
                    String formattedStart = startDateTime.format(outputFormatter);
                    String formattedEnd = endDateTime.format(outputFormatter);

                    
                    System.out.println("Start: " + formattedStart);
                    System.out.println("End: " + formattedEnd);

                    
                    String budgetFileName = "src\\TXT_Files\\budget_" + selectedTrip[0] + "_" + selectedTrip[1].replace(" ", "_").replace(":", "-") + ".txt";
                    BudgetTracker budgetTracker = new BudgetTracker(budgetFileName);
                    budgetTracker.showBudgetOptions();
                    break;
                } else {
                    System.out.print("Invalid choice. Please enter a number between 1 and " + tripList.size() + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); 
            } catch (Exception e) {
                System.err.println("An unexpected error occurred. Please try again.");
            }
        }
    }

}
