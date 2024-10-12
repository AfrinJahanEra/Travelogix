package Seat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Addseat {

    Scanner sc = new Scanner(System.in);
    ArrayList<String> bookedseats = new ArrayList<>();

    // Booking details will now ask for multiple seats and check for availability
    public void bookingDetails(int busIndex) {
        try {
            File f = new File("bus.txt");
            Scanner fileScanner = new Scanner(f);
            int i = 0;
            boolean busFound = false;
            String[] parts = null;
            int row = 0;
            int col = 0;

            // Find the bus
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                if (i == busIndex - 1) {
                    parts = data.split(",");
                    row = Integer.parseInt(parts[3]); // Row count
                    col = Integer.parseInt(parts[4]); // Column count

                    // Extract already booked seats (if any)
                    if (parts.length > 5) {
                        for (int j = 5; j < parts.length; j++) {
                            bookedseats.add(parts[j]);
                        }
                    }

                    busFound = true;
                    break;
                }
                i++;
            }

            if (busFound) {
                System.out.println("Seat matrix before booking:");
                showSeatMatrix(row, col, bookedseats.toArray(new String[0]));

                System.out.print("How many seats would you like to book? ");
                int seatCount = sc.nextInt();

                for (int j = 0; j < seatCount; j++) {
                    System.out.print("Enter seat (e.g., 1A, 2B): ");
                    String seatChoice = sc.next().toUpperCase();

                    // Ensure the seat is not already booked
                    if (!bookedseats.contains(seatChoice)) {
                        bookedseats.add(seatChoice);
                        System.out.println("Seat " + seatChoice + " booked!");
                    } else {
                        System.out.println("Seat " + seatChoice + " is already booked. Please choose another.");
                        j--; // Retry booking
                    }
                }

                // Save the updated booking to the file
                saveToFile(busIndex);
            } else {
                System.out.println("Bus not found.");
            }

            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private void showSeatMatrix(int row, int col, String[] bookedSeats) {
        char[] columns = {'A', 'B', 'C', 'D', 'E', 'F'}; // Adjust for maximum columns

        System.out.println("Seat matrix:");
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < col; j++) {
                String seat = i + "" + columns[j];
                if (isSeatBooked(seat, bookedSeats)) {
                    System.out.print("[X] ");  // Booked seat
                } else {
                    System.out.print("[" + seat + "] ");  // Available seat
                }
            }
            System.out.println();  // Newline after each row
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

    // Save the updated bookings to the file
    public void saveToFile(int busIndex) {
        try {
            // Read all lines into a list to update only the relevant bus line
            File f = new File("bus.txt");
            Scanner fileScanner = new Scanner(f);
            ArrayList<String> lines = new ArrayList<>();

            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
            fileScanner.close();

            // Update the specific bus line with the new booked seats
            String[] parts = lines.get(busIndex - 1).split(",");
            StringBuilder updatedLine = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                updatedLine.append(parts[i]).append(",");
            }
            for (String seat : bookedseats) {
                updatedLine.append(seat).append(",");
            }

            lines.set(busIndex - 1, updatedLine.toString());

            // Write back to the file
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


