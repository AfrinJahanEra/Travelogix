package Transport_Agency;

import java.io.*;
import java.util.Scanner;

public class Delete_Bus {

    public void delete_bus(String busNameToDelete) {
        File inputFile = new File("bus_data.txt");
        File tempFile = new File("temp_bus_data.txt");

        boolean busFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // If we find the bus to delete, we skip writing its details to the temp file
                if (line.contains("Bus Number Plate: " + busNameToDelete)) {
                    busFound = true;
                    System.out.println("Deleting bus: " + busNameToDelete);
                    // Skip writing all the details of the bus being deleted
                    while ((line = reader.readLine()) != null && !line.equals("---------------------------")) {
                        // Skip lines until the next bus entry
                    }
                    continue;  // Continue to the next line
                }
                // Write the lines of buses that are not being deleted
                writer.write(line + "\n");
            }

            if (!busFound) {
                System.out.println("No bus found with the name: " + busNameToDelete);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while deleting the bus.");
            e.printStackTrace();
        }

        // Replace the original file with the updated temp file
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }

        // After deletion, return to the dashboard
        if (busFound) {
            System.out.println("Bus deleted successfully.\n");
        }


        goToDashboard();
    }

    // Simulating return to the Transport Agency dashboard
    private void goToDashboard() {
        TransportAgency dashboard = new TransportAgency();
        dashboard.menu();  // Correct method call to display the dashboard
    }

}
