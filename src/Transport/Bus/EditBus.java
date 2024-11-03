package Transport.Bus;

import Utilities.FileManager.File.FileHandler;

import java.io.IOException;
import java.util.Scanner;

public class EditBus {
    private FileHandler fileHandler;
    private Scanner sc;

    public EditBus(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.sc = new Scanner(System.in);
    }

    public void numberPlate() throws IOException {
        System.out.print("Enter the number plate of the bus to edit: ");
        String numberPlate = sc.nextLine().trim();
        String updatedContent = editBusDetails(numberPlate);
        writeUpdatedContent(updatedContent);
    }

    boolean busFound = false;

    public String editBusDetails(String numberPlate) throws IOException {
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");
        String updatedContent = "";

        for (String line : lines) {
            String[] parts = line.split(",");

            if (parts[4].trim().equals(numberPlate)) {
                busFound = true;
                System.out.println("Current details: " + line);

                updateDetail("Enter new bus name", parts, 0, sc);
                updateDetail("Enter new starting location", parts, 1, sc);
                updateDetail("Enter new ending location", parts, 2, sc);
                updateDetail("Enter new starting time", parts, 3, sc);
                updateDetail("Enter new number plate", parts, 4, sc);
                updateDetail("Enter new phone number", parts, 5, sc);
                updateDetail("Enter new rows", parts, 6, sc);
                updateDetail("Enter new columns", parts, 7, sc);

                updatedContent+=parts[0]+","+parts[1]+","+parts[2]+","+parts[3]+","+parts[4]+","+
                        parts[5]+","+parts[6]+","+parts[7]+"\n";
            } else {
                updatedContent+=line+"\n";
            }
        }
        return updatedContent;
    }

    public void writeUpdatedContent(String updatedContent) throws IOException {
        if (busFound) {
            fileHandler.writeToFile(updatedContent.trim());
            System.out.println("Bus details updated successfully.");
        } else {
            System.out.println("Bus with this number plate was not found.");
        }
    }

    public void updateDetail(String prompt, String[] parts, int index, Scanner scanner) {
        System.out.print(prompt + " (or press Enter to keep current): ");
        String newInfo = scanner.nextLine();
        if (!newInfo.isEmpty()) {
            parts[index] = newInfo;
        }
    }
}

