package Bus;

import java.io.File;
import java.util.Scanner;

public class Viewdetails {

    public void showdetails(int n) {
        try {
            File f = new File("bus.txt");
            Scanner sc = new Scanner(f);
            int i = 0;
            boolean busFound = false;

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                if (i == n - 1) {
                    String[] parts = data.split(",");
                    String busName = parts[0];
                    String startingLocation = parts[1];
                    String endingLocation = parts[2];


                    System.out.println("Bus name: " + busName);
                    System.out.println("Starting location: " + startingLocation);
                    System.out.println("Ending location: " + endingLocation);
                    busFound = true;
                    break;
                }
                i++;
            }

            if (!busFound) {
                System.out.println("Bus not found. Please check the index provided.");
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
