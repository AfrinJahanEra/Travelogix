package Bus;

import java.io.*;
import java.util.*;

public class EditBus {
    private Scanner sc = new Scanner(System.in);

    public void editBus() {
        List<BusDetails> buses = new ArrayList<>();
        String line;

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

        System.out.print("Enter the index of the bus to edit: ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > buses.size()) {
            System.out.println("Invalid choice. Please try again.");
        } else {
            BusDetails selectedBus = buses.get(choice - 1);
            editBusDetails(selectedBus);
            updateBusFile(buses);
        }
    }

    private void editBusDetails(BusDetails bus) {
        System.out.println("\n--- Editing Bus: " + bus.busName + " ---");
        System.out.print("Enter new bus name (leave blank to keep the same): ");
        sc.nextLine(); // Consume leftover newline
        String newBusName = sc.nextLine();
        if (!newBusName.isEmpty()) {
            bus.busName = newBusName;
        }

        System.out.print("Enter new starting location (leave blank to keep the same): ");
        String newStartingLocation = sc.nextLine();
        if (!newStartingLocation.isEmpty()) {
            bus.startingLocation = newStartingLocation;
        }

        System.out.print("Enter new ending location (leave blank to keep the same): ");
        String newEndingLocation = sc.nextLine();
        if (!newEndingLocation.isEmpty()) {
            bus.endingLocation = newEndingLocation;
        }

        System.out.print("Enter new number plate (leave blank to keep the same): ");
        String newNumberPlate = sc.nextLine();
        if (!newNumberPlate.isEmpty()) {
            bus.numberPlate = newNumberPlate;
        }

        System.out.println("Bus details updated successfully!");
    }

    private void updateBusFile(List<BusDetails> buses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("bus_details.txt"))) {
            for (BusDetails bus : buses) {
                writer.println("Bus Name: " + bus.busName);
                writer.println("Starting Location: " + bus.startingLocation);
                writer.println("Ending Location: " + bus.endingLocation);
                writer.println("Number Plate: " + bus.numberPlate);
                writer.println("Seat Matrix:");
                writer.println(bus.seatMatrix); // Write the seat matrix as it was
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the bus details.");
        }
    }
}
