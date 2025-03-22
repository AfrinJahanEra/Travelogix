package Transport.Bus;

import Utilities.FileManager.File.FileHandler;
import java.io.IOException;
import java.util.Scanner;

public class ViewBusDetails {
    private final FileHandler fileHandler;
    private final Scanner sc;

    public ViewBusDetails(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.sc = new Scanner(System.in);
    }

    public void numberPlate() throws IOException {
        System.out.print("Enter the number plate of the bus to view details: ");
        String numberPlate = sc.nextLine().trim();
        String details = viewBusDetails(numberPlate);
        System.out.println(details);
    }

    public String viewBusDetails(String numberPlate) throws IOException {
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");
        StringBuilder output = new StringBuilder();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");

            if (parts[4].trim().equals(numberPlate)) {
                output.append("\n\n");
                output.append("                 BUS DETAILS                  \n");
                output.append("═════════════════════════════════════════════\n");
                output.append(String.format("%-25s: %s\n", "Bus Name", parts[0].trim()));
                output.append(String.format("%-25s: %s\n", "Departure From", parts[1].trim()));
                output.append(String.format("%-25s: %s\n", "Destination", parts[2].trim()));
                output.append(String.format("%-25s: %s\n", "Departure Time", parts[3].trim()));
                output.append(String.format("%-25s: %s\n", "Number Plate", parts[4].trim()));
                output.append(String.format("%-25s: %s\n", "Contact Number", parts[5].trim()));
                output.append("═════════════════════════════════════════════\n");

                if (parts.length <= 8) {
                    output.append("No seats booked yet for this bus.\n");
                } else {
                    output.append("Booked Seats:\n");
                    for (int i = 8; i < parts.length; i++) {
                        output.append(String.format("  - Seat %s\n", parts[i].trim()));
                    }
                }
                found = true;
                break;
            }
        }

        if (!found) {
            output.append("\n\n");
            output.append("           BUS NOT FOUND!                    \n");
        }

        return output.toString();
    }
}
