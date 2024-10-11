package Bus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Editdetails {

    public void editdetails(int n) {
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
                String busName = parts[0].trim();
                String startingLocation = parts[1].trim();
                String endingLocation = parts[2].trim();

                if (i == n - 1) {
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
                }
                busFound = true;
                writer.write(busName + "," + startingLocation + "," + endingLocation + "\n");
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
