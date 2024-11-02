package Source.Seat;
import Source.File.*;

import java.io.IOException;
import java.util.Scanner;

public class SeatBooking {
    private FileHandler fileHandler;
    private Scanner sc;
    private SeatManager seatManager;

    public SeatBooking(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.sc = new Scanner(System.in);
        this.seatManager = new SeatManager();
    }

    public void initiateBooking() throws IOException {
        System.out.print("Enter the number plate of the bus to book seats: ");
        String numberPlate = sc.nextLine().trim();

        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");

        String[] busDetails = findBusDetails(lines, numberPlate);
        if (busDetails != null) {
            seatManager.setupSeatManager(busDetails);
            seatManager.bookSeats(sc);
            fileHandler.writeToFile(seatManager.updateBusDetails(lines, busDetails));
        } else {
            System.out.println("Bus with number plate " + numberPlate + " not found.");
        }
    }

    private String[] findBusDetails(String[] lines, String numberPlate) {
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[4].trim().equalsIgnoreCase(numberPlate)) {
                return parts;
            }
        }
        return null;
    }
}
