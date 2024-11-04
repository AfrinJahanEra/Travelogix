package Traveler;


import Utilities.FileManager.File.FileHandler;
import java.io.IOException;

public class ViewBusDetails {
    private FileHandler fileHandler;

    public ViewBusDetails(String filePath) {
        this.fileHandler = new FileHandler(filePath);
    }

    public String viewBusDetails(String numberPlate) throws IOException {
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");
        
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[4].equals(numberPlate)) {
                return String.format("Bus Name: %s\nStarting Location: %s\nEnding Location: %s\nStarting Time: %s\n" +
                        "Number Plate: %s\nPhone Number: %s\n",
                        parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            }
        }
        return "Bus not found!";
    }
}

