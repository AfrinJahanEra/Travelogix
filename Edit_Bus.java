package Transport_Agency;

import java.io.*;
import java.util.Scanner;

public class Edit_Bus {


    public void edit_bus_name(String busNameToEdit, String newBusName) {
        modifyBusDetails(busNameToEdit, (line) -> {
            if (line.contains("Bus Name: ")) {
                return "Bus Name: " + newBusName;
            }
            return line;
        });
    }


    public void edit_cost_per_seat(String busNameToEdit, double newCostPerSeat) {
        modifyBusDetails(busNameToEdit, (line) -> {
            if (line.contains("Cost per Seat: ")) {
                return "Cost per Seat: " + newCostPerSeat;
            }
            return line;
        });
    }


    public void edit_starting_location(String busNameToEdit, String newStartLocation) {
        modifyBusDetails(busNameToEdit, (line) -> {
            if (line.contains("Starting Location: ")) {
                return "Starting Location: " + newStartLocation;
            }
            return line;
        });
    }


    public void edit_ending_location(String busNameToEdit, String newEndLocation) {
        modifyBusDetails(busNameToEdit, (line) -> {
            if (line.contains("Ending Location: ")) {
                return "Ending Location: " + newEndLocation;
            }
            return line;
        });
    }


    private void modifyBusDetails(String busNameToEdit, BusModifier modifier) {
        File file = new File("bus_data.txt");
        File tempFile = new File("temp_bus_data.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean busFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Bus Name: " + busNameToEdit)) {
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
                System.out.println("No bus found with the name: " + busNameToEdit);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while modifying the bus details.");
            e.printStackTrace();
        }

        if (file.delete()) {
            tempFile.renameTo(file);
        }
    }
    @FunctionalInterface
    interface BusModifier {
        String modify(String line);
    }
}
