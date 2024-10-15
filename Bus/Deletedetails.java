package Bus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Deletedetails {

    public void deleteDetails(String busName, String startingLocation, String endingLocation) {
        try {
            File f = new File("bus.txt");
            Scanner sc = new Scanner(f);
            boolean busFound = false;
            File tempFile = new File("temp_bus.txt");
            FileWriter writer = new FileWriter(tempFile);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] parts = data.split(",");

                if (parts.length < 5) {
                    System.out.println("Data format error in bus.txt. Skipping line.");
                    writer.write(data + "\n");
                    continue;
                }

                String currentBusName = parts[0];
                String currentStartingLocation = parts[1];
                String currentEndingLocation = parts[2];

                // Check if the current bus matches the criteria
                if (currentBusName.equalsIgnoreCase(busName) &&
                        currentStartingLocation.equalsIgnoreCase(startingLocation) &&
                        currentEndingLocation.equalsIgnoreCase(endingLocation)) {
                    busFound = true;
                    System.out.println("Bus \"" + busName + "\" from " + startingLocation + " to " + endingLocation + " deleted.");
                } else {
                    writer.write(data + "\n");
                }
            }

            if (!busFound) {
                System.out.println("Bus not found. Please check the details provided or ensure they are spelled correctly.");
            }

            sc.close();
            writer.close();

            // Delete the original file and rename the temp file
            if (f.delete()) {
                tempFile.renameTo(f);
            } else {
                System.out.println("Failed to delete the original file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
