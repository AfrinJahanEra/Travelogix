package Bus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewBus {

    public void displayAllBuses() {
        List<BusDetails> buses = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader("bus_details.txt"))) {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Bus Name: ")) {
                    String busName = line.substring(9); // Remove "Bus Name: "
                    String startingLocation = reader.readLine().substring(19); // Remove "Starting Location: "
                    String endingLocation = reader.readLine().substring(18); // Remove "Ending Location: "
                    String numberPlate = reader.readLine().substring(14); // Remove "Number Plate: "

                    // Read the seat matrix
                    reader.readLine(); // Skip the "Seat Matrix:" line
                    StringBuilder seatMatrixBuilder = new StringBuilder();
                    for (int i = 0; i < 4; i++) { // Assuming 4 rows of seats in the matrix
                        seatMatrixBuilder.append(reader.readLine()).append("\n");
                    }
                    String seatMatrix = seatMatrixBuilder.toString().trim();

                    // Add bus details to the list
                    buses.add(new BusDetails(busName, startingLocation, endingLocation, numberPlate, seatMatrix));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the bus details.");
            return;
        }

        if (buses.isEmpty()) {
            System.out.println("No buses found.");
            return;
        }

        System.out.println("\n--- List of Buses ---");
        for (int i = 0; i < buses.size(); i++) {
            BusDetails bus = buses.get(i);
            System.out.println((i + 1) + ". " + bus.busName + " from " + bus.startingLocation + " to " + bus.endingLocation);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the index of the bus to view details: ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > buses.size()) {
            System.out.println("Invalid choice. Please try again.");
        } else {
            displayBusDetails(buses.get(choice - 1));
        }
    }

    private void displayBusDetails(BusDetails bus) {
        System.out.println("\n--- Bus Details ---");
        System.out.println("Bus Name: " + bus.busName);
        System.out.println("Starting Location: " + bus.startingLocation);
        System.out.println("Ending Location: " + bus.endingLocation);
        System.out.println("Number Plate: " + bus.numberPlate);
        System.out.println("Seat Matrix: ");
        System.out.println(bus.seatMatrix); // Display the full seat matrix
    }
}


