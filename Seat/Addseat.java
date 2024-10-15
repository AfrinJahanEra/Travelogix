package Seat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Addseat {

    Scanner sc = new Scanner(System.in);
    ArrayList<String> bookedSeats = new ArrayList<>();

    public void bookingDetails(String busName, String startingLocation, String endingLocation) {
        try {
            File f = new File("bus.txt");
            Scanner fileScanner = new Scanner(f);
            boolean busFound = false;
            String[] parts = null;
            int row = 0;
            int col = 0;

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                parts = data.split(",");

                if (parts.length < 5) {
                    System.out.println("Data format error in bus.txt. Skipping line.");
                    continue;
                }

                String currentBusName = parts[0].trim();
                String currentStartingLocation = parts[1].trim();
                String currentEndingLocation = parts[2].trim();

                // Check if the current bus matches the criteria
                if (currentBusName.equalsIgnoreCase(busName) &&
                        currentStartingLocation.equalsIgnoreCase(startingLocation) &&
                        currentEndingLocation.equalsIgnoreCase(endingLocation)) {

                    busFound = true;
                    row = Integer.parseInt(parts[3]);
                    col = Integer.parseInt(parts[4]);

                    if (parts.length > 5) {
                        for (int j = 5; j < parts.length; j++) {
                            bookedSeats.add(parts[j]);
                        }
                    }

                    break;
                }
            }

            if (busFound) {
                System.out.println("Seat matrix before booking:");
                showSeatMatrix(row, col, bookedSeats.toArray(new String[0]));

                System.out.print("How many seats would you like to book? ");
                int seatCount = sc.nextInt();

                for (int j = 0; j < seatCount; j++) {
                    System.out.print("Enter seat (e.g., 1A, 2B): ");
                    String seatChoice = sc.next().toUpperCase();

                    if (!bookedSeats.contains(seatChoice)) {
                        bookedSeats.add(seatChoice);
                        System.out.println("Seat " + seatChoice + " booked!");
                    } else {
                        System.out.println("Seat " + seatChoice + " is already booked. Please choose another.");
                        j--;
                    }
                }

                saveToFile(parts[0]); // Pass the bus name for saving
            } else {
                System.out.println("Bus not found. Please check the details provided.");
            }

            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private void showSeatMatrix(int row, int col, String[] bookedSeats) {
        char[] columns = {'A', 'B', 'C', 'D', 'E', 'F'};

        System.out.println("Seat matrix:");
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < col; j++) {
                String seat = i + "" + columns[j];
                if (isSeatBooked(seat, bookedSeats)) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[" + seat + "] ");
                }
            }
            System.out.println();
        }
    }

    private boolean isSeatBooked(String seat, String[] bookedSeats) {
        for (String bookedSeat : bookedSeats) {
            if (seat.equals(bookedSeat)) {
                return true;
            }
        }
        return false;
    }

    public void saveToFile(String busName) {
        try {
            File f = new File("bus.txt");
            Scanner fileScanner = new Scanner(f);
            ArrayList<String> lines = new ArrayList<>();

            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
            fileScanner.close();

            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts[0].trim().equalsIgnoreCase(busName)) {
                    StringBuilder updatedLine = new StringBuilder();
                    for (int j = 0; j < 5; j++) {
                        updatedLine.append(parts[j]).append(",");
                    }
                    for (String seat : bookedSeats) {
                        updatedLine.append(seat).append(",");
                    }
                    lines.set(i, updatedLine.toString());
                    break;
                }
            }

            FileWriter writer = new FileWriter("bus.txt");
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while saving the seat details: " + e.getMessage());
        }
    }
}
