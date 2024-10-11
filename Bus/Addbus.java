package Bus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Addbus {
    public String busName;
    public String startingLocation;
    public String endingLocation;
    public String numberPlate;

    public void inputBusDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bus name: ");
        busName = sc.nextLine();

        System.out.print("Enter starting location: ");
        startingLocation = sc.nextLine();

        System.out.print("Enter ending location: ");
        endingLocation = sc.nextLine();
    }




    public void saveToFile() {
        try (FileWriter writer = new FileWriter("bus.txt", true)) {
            writer.write(busName + ","+startingLocation+","+endingLocation+"\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the bus details: " + e.getMessage());
        }
    }

}
