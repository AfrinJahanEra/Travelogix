package Bus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Deletedetails {

    public void deletedetails(int n) {
        try {
            File f = new File("bus.txt");
            Scanner sc = new Scanner(f);
            int i = 0;
            boolean busFound = false;
            File tempFile = new File("temp_bus.txt");
            FileWriter writer = new FileWriter(tempFile);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] parts = data.split(",");

                // Ensure the array has enough parts to avoid IndexOutOfBoundsException
                if (parts.length < 5) { // Adjust based on your data structure
                    System.out.println("Data format error in bus.txt. Skipping line.");
                    writer.write(data + "\n"); // Write the line as-is to the temp file
                    continue;
                }

                // Check if the current index matches the one to delete
                if (i == n - 1) {
                    busFound = true;
                    System.out.println("Bus at index " + n + " deleted.");
                    // Skip writing this line to the temp file
                } else {
                    // Write the current bus details to the temp file
                    writer.write(data + "\n");
                }
                i++; // Increment the bus index
            }

            if (!busFound) {
                System.out.println("Bus not found. Please check the index provided.");
            }

            sc.close();
            writer.close();

            // Replace the original file with the updated temporary file
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

