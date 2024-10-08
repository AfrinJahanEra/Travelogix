package Bus;

import java.io.*;
import java.util.*;

public class DeleteBus {
    private Scanner sc = new Scanner(System.in);

    public void deleteBus() {
        List<BusDetails> buses = new ArrayList<>();
        String line;

        // Read all bus details from the file
        try (BufferedReader reader = new BufferedReader(new FileReader("bus_details.txt"))) {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Bus Name: ")) {
                    String busName = line.substring(9);
                    String startingLocation = reader.readLine().substring(19);
                    String endingLocation = reader.readLine().substring(18);
                    String numberPlate = reader.readLine().substring(14);

                    // Read the seat matrix
                    reader.readLine(); // Skip the "Seat Matrix:" line
                    StringBuilder seatMatrixBuilder = new StringBuilder();
                    for (int i = 0; i < 4; i++) { // Assuming 4 rows in the seat matrix
                        seatMatrixBuilder.append(reader.readLine()).append("\n");
                    }
                    String seatMatrix = seatMatrixBuilder.toString().trim();

                    // Add the bus details to the list
                    buses.add(new BusDetails(busName, startingLocation, endingLocation, numberPlate, seatMatrix));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the bus details.");
            return;
        }

        // Check if any buses are available
        if (buses.isEmpty()) {
            System.out.println("No buses found.");
            return;
        }

        // Display all buses
        System.out.println("\n--- List of Buses ---");
        for (int i = 0; i < buses.size(); i++) {
            BusDetails bus = buses.get(i);
            System.out.println((i + 1) + ". " + bus.busName + " from " + bus.startingLocation + " to " + bus.endingLocation);
        }

        // Ask the user to choose which bus to delete
        System.out.print("Enter the index of the bus to delete: ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > buses.size()) {
            System.out.println("Invalid choice. Please try again.");
        } else {
            BusDetails busToDelete = buses.get(choice - 1);
            buses.remove(choice - 1);

            System.out.println("Bus '" + busToDelete.busName + "' has been deleted successfully.");

            // Update the file after deletion
            updateBusFile(buses);
        }
    }

    private void updateBusFile(List<BusDetails> buses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("bus_details.txt"))) {
            for (BusDetails bus : buses) {
                writer.println("Bus Name: " + bus.busName);
                writer.println("Starting Location: " + bus.startingLocation);
                writer.println("Ending Location: " + bus.endingLocation);
                writer.println("Number Plate: " + bus.numberPlate);
                writer.println("Seat Matrix:");
                writer.println(bus.seatMatrix);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the bus details.");
        }
    }
}
