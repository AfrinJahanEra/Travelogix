package Transport_Agency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Search_Bus {
    public void search_bus_by_name(String busNameToSearch) {
        try (BufferedReader reader = new BufferedReader(new FileReader("bus_data.txt"))) {
            String line;
            boolean busFound = false;
            int busIndex = 1;  // Index to track multiple matches

            // Convert input bus name to lowercase for case-insensitive search
            String searchLowerCase = busNameToSearch.toLowerCase();

            while ((line = reader.readLine()) != null) {
                // Convert the line to lowercase and check for the bus name match
                if (line.toLowerCase().contains("bus number plate: " + searchLowerCase)) {
                    busFound = true;
                    System.out.println("Bus " + busIndex + " Details found for: " + busNameToSearch);
                    System.out.println(line);

                    // Print the rest of the bus details until the separator
                    while ((line = reader.readLine()) != null && !line.equals("---------------------------")) {
                        System.out.println(line);
                    }
                    System.out.println("---------------------------");  // Print separator
                    busIndex++;  // Increment bus index for next matching bus
                }
            }

            if (!busFound) {
                System.out.println("No bus found with the name: " + busNameToSearch);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading bus details.");
            e.printStackTrace();
        }
    }
}