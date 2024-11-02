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

    public void viewBusDetails() throws IOException {
        System.out.print("Enter the number plate of the bus to view: ");
        String numberPlate = sc.nextLine().trim();
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");
        String updatedContent = "";
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");

            if (parts[4].trim().equals(numberPlate)) {
                System.out.println("Current details: ");
                System.out.println("Bus name: " + parts[0]);
                System.out.println("Bus starting location: " + parts[1]);
                System.out.println("Bus ending location: " + parts[2]);
                System.out.println("Bus starting time: " + parts[3]);
                System.out.println("Bus number plate: " + parts[4]);
                System.out.println("Bus concact number: " + parts[5]);

                if (parts.length <= 8) {
                    System.out.println("No seats booked yet from this bus");
                } else
                {
                    for(int i=8;i< parts.length;i++)
                    {
                        System.out.println("Booked seats "+parts[i]);
                    }
                }
                found = true;
                break;

            }
        }
        if(!found)
        {
            System.out.println("Bus not found!!");
        }
    }
}
