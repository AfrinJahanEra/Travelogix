package Seat;

import java.io.*;
import java.util.*;
import Bus.*;

public class ShowSeat {
    private Scanner sc = new Scanner(System.in);

    public void displaySeatMatrix() {
        List<BusDetails> buses = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader("bus_details.txt"))) {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Bus Name: ")) {
                    String busName = line.substring(9);
                    String startingLocation = reader.readLine().substring(19);
                    String endingLocation = reader.readLine().substring(18);
                    String numberPlate = reader.readLine().substring(14);


                    StringBuilder seatMatrixBuilder = new StringBuilder();
                    line = reader.readLine();
                    while (!(line = reader.readLine()).equals("----------------------------")) {
                        seatMatrixBuilder.append(line).append("\n");
                    }
                    String seatMatrix = seatMatrixBuilder.toString().trim(); // Convert to string and trim the trailing newline

                    buses.add(new BusDetails(busName, startingLocation, endingLocation, numberPlate, seatMatrix));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the bus details.");
            return;
        }

        if (buses.isEmpty()) {
            System.out.println("No buses found.");
            return;
        }

        System.out.println("\n--- List of Buses ---");
        for (int i = 0; i < buses.size(); i++) {
            BusDetails bus = buses.get(i);
            System.out.println((i + 1) + ". " + bus.busName + " from " + bus.startingLocation + " to " + bus.endingLocation);
        }

        System.out.print("Enter the index of the bus to view seat matrix: ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > buses.size()) {
            System.out.println("Invalid choice. Please try again.");
        } else {
            displayBusSeatMatrix(buses.get(choice - 1).seatMatrix);
        }
    }

    private void displayBusSeatMatrix(String seatMatrix) {
        System.out.println("\n--- Seat Matrix ---");
        System.out.println(seatMatrix);
    }

}


