package Transport.Seat;

import java.util.ArrayList;
import java.util.Scanner;

public class SeatManager {
    private int row;
    private int col;
    private ArrayList<String> bookedSeats;
    private ShowSeat showSeat;
    private BusDetailsUpdater busDetailsUpdater;
    private SeatValidator seatValidator;

    public SeatManager() {
        this.bookedSeats = new ArrayList<>();
        this.showSeat = new ShowSeat();
        this.busDetailsUpdater = new BusDetailsUpdater();
    }

    public void setupSeatManager(String[] busDetails) {
        this.row = Integer.parseInt(busDetails[6]);
        this.col = Integer.parseInt(busDetails[7]);
        this.seatValidator = new SeatValidator(row, col);

        if (busDetails.length > 8) {
            for (int j = 8; j < busDetails.length; j++) {
                bookedSeats.add(busDetails[j].trim());
            }
        }
    }

    public void bookSeats(Scanner sc) {
        System.out.println("Seat matrix before booking:");
        showSeat.displaySeatMatrix(row, col, bookedSeats);

        int seatCount;
        do {
            System.out.print("How many seats would you like to book? ");
            seatCount = sc.nextInt();
            sc.nextLine();
        } while (seatCount <= 0);

        for (int j = 0; j < seatCount; j++) {
            String seatChoice;
            boolean validSeat = false;

            do {
                System.out.print("Enter seat (e.g., 1A, 2B): ");
                seatChoice = sc.nextLine().toUpperCase();
                if (seatValidator.isSeatValid(seatChoice) && !bookedSeats.contains(seatChoice)) {
                    bookedSeats.add(seatChoice);
                    System.out.println("Seat " + seatChoice + " booked!");
                    validSeat = true;
                } else {
                    System.out.println("Invalid seat or already booked. Please select again.");
                }
            } while (!validSeat);
        }
    }

    public String updateBusDetails(String[] lines, String[] originalParts) {
        return busDetailsUpdater.updateBusDetails(lines, originalParts, bookedSeats.toArray(new String[0]));
    }
}
