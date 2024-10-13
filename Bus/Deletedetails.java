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


                if (parts.length < 5) {
                    System.out.println("Data format error in bus.txt. Skipping line.");
                    writer.write(data + "\n");
                    continue;
                }


                if (i == n - 1) {
                    busFound = true;
                    System.out.println("Bus at index " + n + " deleted.");

                } else {

                    writer.write(data + "\n");
                }
                i++;
            }

            if (!busFound) {
                System.out.println("Bus not found. Please check the index provided.");
            }

            sc.close();
            writer.close();


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

