package Bus;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Viewdetails {

    public void showdetails(int busIndex) {
        try {
            File f = new File("bus.txt");
            Scanner fileScanner = new Scanner(f);
            int i = 0;
            boolean busFound = false;
            String[] parts = null;
            int row = 0, col = 0;
            ArrayList<String> bookedSeats = new ArrayList<>();

            // Find the correct bus in the file
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                if (i == busIndex - 1) {
                    parts = data.split(",");
                    String busName = parts[0];
                    String startingLocation = parts[1];
                    String endingLocation = parts[2];
                    row = Integer.parseInt(parts[3]);
                    col = Integer.parseInt(parts[4]);

                    if (parts.length > 5) {
                        for (int j = 5; j < parts.length; j++) {
                            bookedSeats.add(parts[j]);
                        }
                    }

                    System.out.println("Bus Name: " + busName);
                    System.out.println("Starting Location: " + startingLocation);
                    System.out.println("Ending Location: " + endingLocation);
                    System.out.println("Seat matrix: ");
                    showSeatMatrix(row, col, bookedSeats.toArray(new String[0]));

                    if (bookedSeats.isEmpty()) {
                        System.out.println("No seats booked yet.");
                    } else {
                        System.out.println("Booked seats: " + String.join(", ", bookedSeats));
                    }

                    busFound = true;
                    break;
                }
                i++;
            }

            if (!busFound) {
                System.out.println("Bus not found. Please check the index provided.");
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showSeatMatrix(int row, int col, String[] bookedSeats) {
        char[] columns = {'A', 'B', 'C', 'D', 'E', 'F'};  // Adjust for maximum columns

        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < col; j++) {
                String seat = i + "" + columns[j];
                if (isSeatBooked(seat, bookedSeats)) {
                    System.out.print("[X] ");  // Booked seat
                } else {
                    System.out.print("[" + seat + "] ");  // Available seat
                }
            }
            System.out.println();  // New line after each row
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
}

