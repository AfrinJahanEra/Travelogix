package Source.Bus;
import Source.File.FileHandler;

import java.io.IOException;
import java.util.Scanner;

public class AddBus {
    public String busName;
    public String startingLocation;
    public String endingLocation;
    public String startingTime;
    public String numberPlate;
    public String phoneNumber;
    public int row;
    public int col;

    private FileHandler fileHandler;

    public AddBus(String filePath) {
        this.fileHandler = new FileHandler(filePath);
    }

    public void inputBusDetails() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bus name: ");
        busName = sc.nextLine();

        System.out.print("Enter starting location: ");
        startingLocation = sc.nextLine();

        System.out.print("Enter ending location: ");
        endingLocation = sc.nextLine();

        System.out.print("Enter starting time: ");
        startingTime = sc.nextLine();

        System.out.println("Enter number plate: ");
        numberPlate = sc.nextLine();

        System.out.println("Enter phone number: ");
        phoneNumber = sc.nextLine();

        System.out.print("Enter row numbers: ");
        row = sc.nextInt();

        System.out.print("Enter column numbers: ");
        col = sc.nextInt();

        savedetails();
    }

    private void savedetails() throws IOException {
        String s = busName + "," + startingLocation + "," + endingLocation + ","
                + startingTime + "," + numberPlate + "," +phoneNumber+","+ row + "," + col;
        fileHandler.appendToFile(s);
        System.out.println("Added successfully!");
    }
}
