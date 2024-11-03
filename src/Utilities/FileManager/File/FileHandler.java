package Utilities.FileManager.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data + "\n");
        }
    }

    public String readFromFile() throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }
        return fileContent.toString();
    }

    public void appendToFile(String data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(data + "\n");
        }
    }
}
