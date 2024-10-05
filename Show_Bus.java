package Transport_Agency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Show_Bus {

    public void show_buses_by_route(String startLocationToSearch, String endLocationToSearch) {
        try (BufferedReader reader = new BufferedReader(new FileReader("bus_data.txt"))) {
            String line;
            boolean busFound = false;
            boolean matchStart = false, matchEnd = false;

            startLocationToSearch = startLocationToSearch.toLowerCase();
            endLocationToSearch = endLocationToSearch.toLowerCase();

            System.out.println("Searching for buses from " + startLocationToSearch + " to " + endLocationToSearch + "...\n");

            while ((line = reader.readLine()) != null) {
                if (line.contains("Bus Details:")) {
                    busFound = false;  // Reset flags for a new bus
                    matchStart = false;
                    matchEnd = false;
                }


                String lowerCaseLine = line.toLowerCase();


                if (lowerCaseLine.contains("starting location: " + startLocationToSearch)) {
                    matchStart = true;
                }


                if (lowerCaseLine.contains("ending location: " + endLocationToSearch)) {
                    matchEnd = true;
                }


                if (matchStart && matchEnd) {
                    busFound = true;
                }


                if (busFound) {
                    System.out.println(line);


                    if (line.equals("---------------------------")) {
                        busFound = false;
                    }
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
