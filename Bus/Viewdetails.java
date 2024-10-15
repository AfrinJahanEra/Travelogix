package Bus;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Viewdetails {

    public void showdetails(String busNameToSearch, String startLocationToSearch, String endLocationToSearch) {
        try {
            File f = new File("bus.txt");
            Scanner fileScanner = new Scanner(f);
            boolean busFound = false;
            String[] parts = null;
            int row = 0, col = 0;
            ArrayList<String> bookedSeats = new ArrayList<>();

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                parts = data.split(",");
                String busName = parts[0].trim();
                String startingLocation = parts[1].trim();
                String endingLocation = parts[2].trim();

                // Check if the current bus matches the search criteria
                if (busName.equalsIgnoreCase(busNameToSearch) &&
                        startingLocation.equalsIgnoreCase(startLocationToSearch) &&
                        endingLocation.equalsIgnoreCase(endLocationToSearch)) {

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
            }

            if (!busFound) {
                // Output appropriate error messages
                if (!busExistsInFile("bus.txt", busNameToSearch, 0)) {
                    System.out.println("No bus with name '" + busNameToSearch + "' exists.");
                } else if (!busExistsInFile("bus.txt", startLocationToSearch, 1)) {
                    System.out.println("No starting location '" + startLocationToSearch + "' exists.");
                } else if (!busExistsInFile("bus.txt", endLocationToSearch, 2)) {
                    System.out.println("No ending location '" + endLocationToSearch + "' exists.");
                } else {
                    System.out.println("You might have misspelled the bus name or one of the locations.");
                }
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to show the seat matrix
    private void showSeatMatrix(int row, int col, String[] bookedSeats) {
        char[] columns = {'A', 'B', 'C', 'D', 'E', 'F'};

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

    // Helper method to check if a seat is booked
    private boolean isSeatBooked(String seat, String[] bookedSeats) {
        for (String bookedSeat : bookedSeats) {
            if (seat.equals(bookedSeat)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if a specific value exists in the file in a specific column
    private boolean busExistsInFile(String fileName, String valueToSearch, int columnIndex) {
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts[columnIndex].trim().equalsIgnoreCase(valueToSearch)) {
                    return true;
                }
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
