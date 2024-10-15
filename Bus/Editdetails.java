package Bus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Editdetails {

    public void editDetails(String busName, String startingLocation, String endingLocation) {
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

                String currentBusName = parts[0].trim();
                String currentStartingLocation = parts[1].trim();
                String currentEndingLocation = parts[2].trim();
                int row = Integer.parseInt(parts[3].trim());
                int col = Integer.parseInt(parts[4].trim());

                // Check if the current bus matches the criteria
                if (currentBusName.equalsIgnoreCase(busName) &&
                        currentStartingLocation.equalsIgnoreCase(startingLocation) &&
                        currentEndingLocation.equalsIgnoreCase(endingLocation)) {
                    busFound = true;
                    Scanner sc1 = new Scanner(System.in);

                    System.out.print("Do you want to change bus name? (yes/no): ");
                    String yes = sc1.nextLine();
                    if (yes.equalsIgnoreCase("yes")) {
                        System.out.print("Enter new bus name: ");
                        busName = sc1.nextLine();
                    }

                    System.out.print("Do you want to change bus starting location? (yes/no): ");
                    yes = sc1.nextLine();
                    if (yes.equalsIgnoreCase("yes")) {
                        System.out.print("Enter new starting location: ");
                        startingLocation = sc1.nextLine();
                    }

                    System.out.print("Do you want to change bus ending location? (yes/no): ");
                    yes = sc1.nextLine();
                    if (yes.equalsIgnoreCase("yes")) {
                        System.out.print("Enter new ending location: ");
                        endingLocation = sc1.nextLine();
                    }

                    System.out.print("Do you want to change number of rows? (yes/no): ");
                    yes = sc1.nextLine();
                    if (yes.equalsIgnoreCase("yes")) {
                        System.out.print("Enter new number of rows: ");
                        row = Integer.parseInt(sc1.nextLine());
                    }

                    System.out.print("Do you want to change number of columns? (yes/no): ");
                    yes = sc1.nextLine();
                    if (yes.equalsIgnoreCase("yes")) {
                        System.out.print("Enter new number of columns: ");
                        col = Integer.parseInt(sc1.nextLine());
                    }

                    // Write the updated bus details
                    writer.write(busName + "," + startingLocation + "," + endingLocation + "," + row + "," + col + "\n");
                } else {
                    // Write the original bus details if no edit is made
                    writer.write(currentBusName + "," + currentStartingLocation + "," + currentEndingLocation + "," + row + "," + col + "\n");
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
