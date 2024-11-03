package Source.Bus;

import Source.File.FileHandler;

import java.io.IOException;
import java.util.Scanner;

public class ViewBusDetails {
    private FileHandler fileHandler;
    private Scanner sc;

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
        String output = "";
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");

            if (parts[4].trim().equals(numberPlate)) {
                output+="Current details:\n";
                output+="Bus name: "+(parts[0])+"\n";
                output+="Bus starting location: "+(parts[1])+"\n";
                output+="Bus ending location: "+(parts[2])+"\n";
                output+="Bus starting time: "+(parts[3])+"\n";
                output+="Bus number plate: "+(parts[4])+"\n";
                output+="Bus contact number: "+(parts[5])+"\n";

                if (parts.length <= 8) {
                    output+="No seats booked yet from this bus\n";
                } else {
                    for (int i = 8; i < parts.length; i++) {
                        output+="Booked seats "+(parts[i])+"\n";
                    }
                }
                found = true;
                break;
            }
        }
        if (!found) {
            output+="Bus not found!!\n";
        }
        return output;
    }
}