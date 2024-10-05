package Transport_Agency;

import java.io.*;
import java.util.Scanner;

public class Create_Bus {


    public void create_bus() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter Starting Location: ");
        String startLocation = scanner.nextLine();

        System.out.println("Enter Ending Location: ");
        String endLocation = scanner.nextLine();

        System.out.println("Enter Route Stoppage 1: ");
        String route1 = scanner.nextLine();

        System.out.println("Enter Route Stoppage 2: ");
        String route2 = scanner.nextLine();

        System.out.println("Enter Route Stoppage 3: ");
        String route3 = scanner.nextLine();

        System.out.println("Enter Bus Name: ");
        String busName = scanner.nextLine();

        System.out.println("Enter Seat Count: ");
        int seatCount = scanner.nextInt();

        System.out.println("Enter Cost per Seat: ");
        double costPerSeat = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter Starting Time: ");
        String startTime = scanner.nextLine();

        System.out.println("Enter Ending Time: ");
        String endTime = scanner.nextLine();

        System.out.println("Enter Date: ");
        String date = scanner.nextLine();

        System.out.println("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();

        System.out.println("Enter Bus Number Plate: ");
        String numberPlate = scanner.nextLine();


        String[][] seats = {
                {"A1", "A2", "A3", "A4", "A5"},
                {"B1", "B2", "B3", "B4", "B5"},
                {"C1", "C2", "C3", "C4", "C5"},
                {"D1", "D2", "D3", "D4", "D5"}
        };


        try (FileWriter writer = new FileWriter("bus_data.txt", true)) {
            writer.write("Bus Details:\n");
            writer.write("Starting Location: " + startLocation + "\n");
            writer.write("Ending Location: " + endLocation + "\n");
            writer.write("Routes: " + route1 + ", " + route2 + ", " + route3 + "\n");
            writer.write("Bus Name: " + busName + "\n");
            writer.write("Seat Count: " + seatCount + "\n");
            writer.write("Cost per Seat: " + costPerSeat + "\n");
            writer.write("Starting Time: " + startTime + "\n");
            writer.write("Ending Time: " + endTime + "\n");
            writer.write("Date: " + date + "\n");
            writer.write("Contact Number: " + contactNumber + "\n");
            writer.write("Bus Number Plate: " + numberPlate + "\n");


            writer.write("Seat Availability:\n");
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    writer.write(seats[i][j] + "  ");
                }
                writer.write("\n");
            }
            writer.write("---------------------------\n");
            System.out.println("Bus details including seat availability saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving bus details.");
            e.printStackTrace();
        }
    }
}