package Transport_Agency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Search_Bus{
    public void search_bus_by_name(String busNameToSearch) {
        try (BufferedReader reader = new BufferedReader(new FileReader("bus_data.txt"))) {
            String line;
            boolean busFound = false;


            while ((line = reader.readLine()) != null) {

                if (line.contains("Bus Name: " + busNameToSearch)) {
                    busFound = true;
                    System.out.println("Bus Details found for: " + busNameToSearch);
                    System.out.println(line);


                    while ((line = reader.readLine()) != null && !line.equals("---------------------------")) {
                        System.out.println(line);
                    }
                    break;
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
