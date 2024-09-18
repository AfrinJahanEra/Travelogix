package TransportAgencyModule;

import java.util.Random;

public class SeatManager {
    private int[][] seats;
    private int emptySeats;

    public SeatManager(int rows, int cols) {
        seats = new int[rows][cols];
        emptySeats = rows * cols;
        // Initialize all seats as empty (0)
    }

    public void displaySeats() {
        System.out.println("Bus Seat Matrix:");
        for (int[] row : seats) {
            for (int seat : row) {
                if (seat == 0) System.out.print("[ ] ");
                else System.out.print("[X] ");
            }
            System.out.println();
        }
    }

    public void bookSeat(int row, int col) {
        if (seats[row][col] == 0) {
            seats[row][col] = 1; // Seat booked
            emptySeats--;
        } else {
            System.out.println("Seat is already booked.");
        }
    }

    public void cancelSeat(int row, int col) {
        if (seats[row][col] == 1) {
            seats[row][col] = 0; // Seat canceled
            emptySeats++;
        } else {
            System.out.println("Seat is not booked.");
        }
    }

    public int getEmptySeats() {
        return emptySeats;
    }

    public int getRandomSeatPrice() {
        Random rand = new Random();
        return rand.nextInt(1000) + 500; // Random between 500 to 1500
    }
}
