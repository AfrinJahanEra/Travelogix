package Bus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Addbus {
    public String busName;
    public String startingLocation;
    public String endingLocation;
    public int row;
    public int col;

    public void inputBusDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bus name: ");
        busName = sc.nextLine();

        System.out.print("Enter starting location: ");
        startingLocation = sc.nextLine();

        System.out.print("Enter ending location: ");
        endingLocation = sc.nextLine();

        System.out.print("Enter row numbers: ");
        row = sc.nextInt();

        System.out.print("Enter column numbers: ");
        col = sc.nextInt();
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter("bus.txt", true)) {
            // Write bus details
            writer.write(busName + "," + startingLocation + "," + endingLocation + "," + row + "," + col + ",");
            // Leave a placeholder for booked seats
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the bus details: " + e.getMessage());
        }
    }
}

