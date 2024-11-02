package Source.Bus;
import Source.File.FileHandler;

import java.io.IOException;
import java.util.Scanner;

public class EditBus {
    private FileHandler fileHandler;
    private Scanner sc;

    public EditBus(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.sc = new Scanner(System.in);
    }

    public void editBusDetails() throws IOException {
        System.out.print("Enter the number plate of the bus to edit: ");
        String numberPlate = sc.nextLine().trim();
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");
        String updatedContent = "";
        boolean busFound = false;

        for (String line : lines) {
            String[] parts = line.split(",");

            if (parts[4].trim().equals(numberPlate)) {
                busFound = true;
                System.out.println("Current details: " + line);

                updateDetail("Enter new bus name", parts, 0);
                updateDetail("Enter new starting location", parts, 1);
                updateDetail("Enter new ending location", parts, 2);
                updateDetail("Enter new starting time", parts, 3);
                updateDetail("Enter new number plate", parts, 4);
                updateDetail("Enter new phone number", parts, 5);
                updateDetail("Enter new rows", parts, 6);
                updateDetail("Enter new columns", parts, 7);

                updatedContent += parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + ","+parts[5]+","+parts[6]+","+parts[7]+"\n";
            } else {
                updatedContent += line + "\n";
            }
        }

        if (busFound) {
            fileHandler.writeToFile(updatedContent.trim());
            System.out.println("Bus details updated successfully.");
        } else {
            System.out.println("Bus with number plate " + numberPlate + " not found.");
        }
    }

    private void updateDetail(String prompt, String[] parts, int index) {
        System.out.print(prompt + " (or press Enter to keep current): ");
        String newInfo = sc.nextLine();
        if (!newInfo.isEmpty()) {
            parts[index] = newInfo;
        }
    }
}
