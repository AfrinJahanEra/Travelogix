package Source.Bus;
import Source.File.FileHandler;

import java.io.IOException;
import java.util.Scanner;

public class DeleteBus {
    private FileHandler fileHandler;
    private Scanner sc;

    public DeleteBus(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.sc = new Scanner(System.in);
    }

    public void deleteBus() throws IOException {
        System.out.print("Enter the number plate of the bus to delete: ");
        String numberPlate = sc.nextLine().trim();
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");
        String updatedContent = "";
        boolean busFound = false;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[4].trim().equals(numberPlate)) {
                busFound = true;
                System.out.println("Bus with number plate " + numberPlate + " has been deleted.");
            } else {

                updatedContent += line + "\n";
            }
        }

        if (busFound) {
            fileHandler.writeToFile(updatedContent.trim());
            System.out.println("Bus deletion successful.");
        } else {
            System.out.println("Bus with number plate " + numberPlate + " not found.");
        }
    }
}
