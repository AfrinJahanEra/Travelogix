package Bus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AddBus {
    private String busName;
    private String startingLocation;
    private String endingLocation;
    private String numberPlate;
    private String[][] seatMatrix;

    public AddBus() {
        // Initialize the seat matrix with seat labels A1 to D5
        seatMatrix = new String[4][5];
        char row = 'A';
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                seatMatrix[i][j] = row + String.valueOf(j + 1);
            }
            row++;
        }
    }

    // Method to input the bus details from the user
    public void inputBusDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bus name: ");
        busName = sc.nextLine().trim();

        System.out.print("Enter starting location: ");
        startingLocation = sc.nextLine().trim();

        System.out.print("Enter ending location: ");
        endingLocation = sc.nextLine().trim();

        System.out.print("Enter bus number plate: ");
        numberPlate = sc.nextLine().trim();
    }

    // Method to display the bus details and seat matrix
    public void displayBusDetails() {
        System.out.println("\n--- Bus Details ---");
        System.out.println("Bus Name: " + busName);
        System.out.println("Starting Location: " + startingLocation);
        System.out.println("Ending Location: " + endingLocation);
        System.out.println("Number Plate: " + numberPlate);
        System.out.println("Seat Matrix:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(seatMatrix[i][j] + " ");
            }
            System.out.println(); // New line after each row of the matrix
        }
    }

    // Method to save the bus details and seat matrix to a file
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("bus_details.txt", true)) {
            writer.write("Bus Name: " + busName + "\n");
            writer.write("Starting Location: " + startingLocation + "\n");
            writer.write("Ending Location: " + endingLocation + "\n");
            writer.write("Number Plate: " + numberPlate + "\n");
            writer.write("Seat Matrix:\n");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    writer.write(seatMatrix[i][j] + " "); // Write each seat label
                }
                writer.write("\n"); // New line after each row
            }
            writer.write("----------------------------\n"); // Divider between bus entries
        } catch (IOException e) {
            System.out.println("An error occurred while saving the bus details: " + e.getMessage());
        }
    }

    // Example usage (main method to test the class)
    public static void main(String[] args) {
        AddBus bus = new AddBus();
        bus.inputBusDetails(); // Take input for bus details
        bus.displayBusDetails(); // Display bus details for verification
        bus.saveToFile(); // Save bus details to file
    }
}
