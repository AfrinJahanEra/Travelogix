package Transport_Agency;

import java.io.*;
import java.util.Scanner;

public class Edit_Bus {

    public void editBusOptions(String busNumberPlateToEdit) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("What would you like to edit for the bus with number plate: " + busNumberPlateToEdit);
            System.out.println("1. Edit Bus Name");
            System.out.println("2. Edit Cost per Seat");
            System.out.println("3. Edit Starting Location");
            System.out.println("4. Edit Ending Location");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter new bus name: ");
                    String newBusName = scanner.nextLine();
                    edit_bus_name(busNumberPlateToEdit, newBusName);
                    System.out.println("Bus name updated.");
                    break;

                case 2:
                    System.out.print("Enter new cost per seat: ");
                    double newCostPerSeat = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline
                    edit_cost_per_seat(busNumberPlateToEdit, newCostPerSeat);
                    System.out.println("Cost per seat updated.");
                    break;

                case 3:
                    System.out.print("Enter new starting location: ");
                    String newStartLocation = scanner.nextLine();
                    edit_starting_location(busNumberPlateToEdit, newStartLocation);
                    System.out.println("Starting location updated.");
                    break;

                case 4:
                    System.out.print("Enter new ending location: ");
                    String newEndLocation = scanner.nextLine();
                    edit_ending_location(busNumberPlateToEdit, newEndLocation);
                    System.out.println("Ending location updated.");
                    break;

                case 5:
                    System.out.println("Exiting... Returning to Transport Agency dashboard.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);  // Loop until "Exit" is chosen
    }

    // Edit bus name by number plate
    public void edit_bus_name(String busNumberPlateToEdit, String newBusName) {
        modifyBusDetails(busNumberPlateToEdit, (line) -> {
            if (line.contains("Bus Name: ")) {
                return "Bus Name: " + newBusName;
            }
            return line;
        });
    }

    // Edit cost per seat by number plate
    public void edit_cost_per_seat(String busNumberPlateToEdit, double newCostPerSeat) {
        modifyBusDetails(busNumberPlateToEdit, (line) -> {
            if (line.contains("Cost per Seat: ")) {
                return "Cost per Seat: " + newCostPerSeat;
            }
            return line;
        });
    }

    // Edit starting location by number plate
    public void edit_starting_location(String busNumberPlateToEdit, String newStartLocation) {
        modifyBusDetails(busNumberPlateToEdit, (line) -> {
            if (line.contains("Starting Location: ")) {
                return "Starting Location: " + newStartLocation;
            }
            return line;
        });
    }

    // Edit ending location by number plate
    public void edit_ending_location(String busNumberPlateToEdit, String newEndLocation) {
        modifyBusDetails(busNumberPlateToEdit, (line) -> {
            if (line.contains("Ending Location: ")) {
                return "Ending Location: " + newEndLocation;
            }
            return line;
        });
    }

    // Utility method to modify bus details by the number plate
    private void modifyBusDetails(String busNumberPlateToEdit, BusModifier modifier) {
        File file = new File("bus_data.txt");
        File tempFile = new File("temp_bus_data.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean busFound = false;

            while ((line = reader.readLine()) != null) {
                // Search for the bus by number plate
                if (line.contains("Number Plate: " + busNumberPlateToEdit)) {
                    busFound = true;
                    writer.write(line + "\n");
                    while ((line = reader.readLine()) != null && !line.equals("---------------------------")) {
                        // Apply changes via the modifier
                        writer.write(modifier.modify(line) + "\n");
                    }
                    writer.write("---------------------------\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            if (!busFound) {
                System.out.println("No bus found with the number plate: " + busNumberPlateToEdit);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while modifying the bus details.");
            e.printStackTrace();
        }

        // Replace old file with updated temp file
        if (file.delete()) {
            tempFile.renameTo(file);
        }
    }

    @FunctionalInterface
    interface BusModifier {
        String modify(String line);
    }
}


