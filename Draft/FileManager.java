package Draft;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "users.txt";

    public void saveUser(String email, String password, String contact, String role) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(email + "," + password + "," + contact + "," + role + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving user data.");
        }
    }

    public String[] getUser(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[0].equals(email)) {
                    return details; // Return user details if email matches
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading user data.");
        }
        return null; // Return null if user is not found
    }

    
    // Read lines from a file
    public String[] readFileLines(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            return null;
        }
    }

    // Write data to a file
    public void writeToFile(String fileName, String data) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }


    // Remove a specific line from a file
    public void removeLineFromFile(String fileName, String lineToRemove) {
        File inputFile = new File(fileName);
        File tempFile = new File("temp_" + fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            // Replace old file with new one
            if (!inputFile.delete()) {
                System.out.println("Could not delete file: " + fileName);
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file: " + fileName);
            }

        } catch (IOException e) {
            System.out.println("Error processing file: " + fileName);
        }
    }

}
