package Transport_Agency;

import java.io.*;

public class Delete_Bus {

    public void delete_bus_by_name(String busNameToDelete) {
        File inputFile = new File("bus_data.txt");
        File tempFile = new File("temp_bus_data.txt");

        boolean busFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean isBusSection = false;

            while ((line = reader.readLine()) != null) {

                if (line.contains("Bus Name: " + busNameToDelete)) {
                    busFound = true;
                    isBusSection = true;
                    System.out.println("Bus '" + busNameToDelete + "' found and deleted.");
                }


                if (isBusSection) {
                    if (line.equals("---------------------------")) {
                        isBusSection = false;
                    }
                    continue;
                }


                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while deleting the bus.");
            e.printStackTrace();
        }

        if (busFound) {

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
                System.out.println("Could not delete the original file.");
            }
        } else {

            tempFile.delete();
            System.out.println("No bus found with the name: " + busNameToDelete);
        }
    }
}

