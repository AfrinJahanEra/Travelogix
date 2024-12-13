package Transport.Bus;

import Utilities.FileManager.File.FileHandler;
import java.io.IOException;
import java.util.Scanner;

public class DeleteBus {
    private FileHandler fileHandler;
    private Scanner sc;

    public DeleteBus(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.sc = new Scanner(System.in);
    }

    public void numberPlate() throws IOException {
        String numberPlate;
        while (true) {
            System.out.print("Enter the number plate of the bus to delete: ");
            numberPlate = sc.nextLine().trim();
            if (!numberPlate.isEmpty()) {
                break;
            } else {
                System.out.println("Number plate cannot be empty. Please enter a valid number plate.");
            }
        }
        deleteBus(numberPlate);
    }

    public void deleteBus(String numberPlate) throws IOException {
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");
        StringBuilder updatedContent = new StringBuilder();
        boolean busFound = false;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length > 4 && parts[4].trim().equals(numberPlate)) {
                busFound = true;
                System.out.println("Bus with number plate " + numberPlate + " has been deleted.");
            } else {
                updatedContent.append(line).append("\n");
            }
        }

        if (busFound) {
            fileHandler.writeToFile(updatedContent.toString().trim());
            System.out.println("Bus deletion successful.");
        } else {
            System.out.println("Bus with number plate " + numberPlate + " not found. Please try again.");
            numberPlate(); // Retry deletion
        }
    }
}

