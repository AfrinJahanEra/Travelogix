package Seat;

import java.io.*;
import java.util.*;
import Bus.*;

public class EditSeat {
    private Scanner sc = new Scanner(System.in);

    public void bookSeats() {
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
                    String seatMatrix = seatMatrixBuilder.toString().trim(); // Convert to string and trim

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

        System.out.print("Enter the index of the bus to book seats: ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > buses.size()) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }


        String seatMatrix = buses.get(choice - 1).seatMatrix;
        System.out.println("\n--- Seat Matrix ---");
        System.out.println(seatMatrix);


        System.out.print("How many seats do you want to book? ");
        int seatCount = sc.nextInt();
        sc.nextLine();

        Set<String> bookedSeats = new HashSet<>();
        for (int i = 0; i < seatCount; i++) {
            System.out.print("Enter seat " + (i + 1) + ": ");
            String seatInput = sc.nextLine().trim().toUpperCase();


            if (!isValidSeat(seatInput)) {
                System.out.println("Invalid seat format. Please enter a valid seat (e.g., A1, B2).");
                i--;
                continue;
            }


            if (bookedSeats.contains(seatInput)) {
                System.out.println("Seat " + seatInput + " is already booked. Please choose a different seat.");
                i--;
                continue;
            }

            bookedSeats.add(seatInput);
        }


        updateSeatMatrix(buses.get(choice - 1), bookedSeats);
    }

    private boolean isValidSeat(String seat) {

        return seat.matches("^[A-D][1-5]$");
    }

    private void updateSeatMatrix(BusDetails bus, Set<String> bookedSeats) {

        List<String> busDetails = new ArrayList<>();
        String line;
        boolean seatMatrixStarted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("bus_details.txt"))) {
            while ((line = reader.readLine()) != null) {
                if (line.contains("Bus Name: " + bus.busName)) {
                    seatMatrixStarted = true;
                }
                busDetails.add(line);


                if (seatMatrixStarted && line.equals("----------------------------")) {
                    busDetails.add(line);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the bus details.");
            return;
        }


        String[] seatMatrixRows = bus.seatMatrix.split("\n");
        for (String bookedSeat : bookedSeats) {
            int row = bookedSeat.charAt(0) - 'A'; // Convert letter to index (A=0, B=1, ...)
            int col = Integer.parseInt(bookedSeat.substring(1)) - 1; // Convert string index to integer (1-5 to 0-4)
            seatMatrixRows[row] = seatMatrixRows[row].replace(bookedSeat, "XX"); // Mark the seat as booked
        }


        try (FileWriter writer = new FileWriter("bus_details.txt")) {
            for (String busDetail : busDetails) {
                writer.write(busDetail + "\n");
                if (busDetail.equals("----------------------------")) {
                    writer.write("Seat Matrix:\n");
                    for (String seatRow : seatMatrixRows) {
                        writer.write(seatRow + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the bus details.");
        }
    }


}
