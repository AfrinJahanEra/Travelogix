package Traveler;


import Utilities.FileManager.File.FileHandler;
import java.io.IOException;

public class ViewBusList {
    private FileHandler fileHandler;

    public ViewBusList(String filePath) {
        this.fileHandler = new FileHandler(filePath);
    }

    public String list(int columnIndex) {
        StringBuilder output = new StringBuilder();
        try {
            String content = fileHandler.readFromFile();
            String[] lines = content.split("\n");

            for (String line : lines) {
                String[] parts = line.split(",");
                String busInfo = String.format("%s - Starting: %s, Ending: %s", 
                                  parts[0], parts[1], parts[2]);
                output.append(busInfo).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
