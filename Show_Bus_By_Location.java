package Transport_Agency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Show_Bus_By_Location {

    public void search_bus_by_location(String startLocationToSearch, String endLocationToSearch) {
        try (BufferedReader reader = new BufferedReader(new FileReader("bus_data.txt"))) {
            String line;
            boolean busFound = false;
            boolean matchStart = false, matchEnd = false;
            int busIndex = 1;  // Index to track multiple matches

            // Convert input locations to lowercase for case-insensitive search
            String startLocationLowerCase = startLocationToSearch.toLowerCase();
            String endLocationLowerCase = endLocationToSearch.toLowerCase();

            System.out.println("Searching for buses from " + startLocationToSearch + " to " + endLocationToSearch + "...\n");

            while ((line = reader.readLine()) != null) {
                // Reset match flags for each new bus
                if (line.contains("Bus Name:")) {
                    matchStart = false;
                    matchEnd = false;
                }

                // Convert the line to lowercase for case-insensitive search
                String lowerCaseLine = line.toLowerCase();

                // Check if the line contains the start location
                if (lowerCaseLine.contains("starting location: " + startLocationLowerCase)) {
                    matchStart = true;
                }

                // Check if the line contains the end location
                if (lowerCaseLine.contains("ending location: " + endLocationLowerCase)) {
                    matchEnd = true;
                }

                // If both start and end locations match, print the bus details
                if (matchStart && matchEnd) {
                    busFound = true;
                    System.out.println("Bus " + busIndex + " Details:");
                    System.out.println(line);  // Print bus name

                    // Print the rest of the bus details until the separator
                    while ((line = reader.readLine()) != null && !line.equals("---------------------------")) {
                        System.out.println(line);
                    }
                    System.out.println("---------------------------");  // Print separator
                    busIndex++;  // Increment bus index for next matching bus

                    // Reset the match flags for the next bus search
                    matchStart = false;
                    matchEnd = false;
                }
            }

            if (!busFound) {
                System.out.println("No buses found from " + startLocationToSearch + " to " + endLocationToSearch);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading bus details.");
            e.printStackTrace();
        }
    }
}
