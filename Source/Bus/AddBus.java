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

        String s ="";
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bus name: ");
        busName = sc.nextLine();
        s+=busName+",";

        System.out.print("Enter starting location: ");
        startingLocation = sc.nextLine();
        s+=startingLocation+",";

        System.out.print("Enter ending location: ");
        endingLocation = sc.nextLine();
        s+=endingLocation+",";

        System.out.print("Enter starting time: ");
        startingTime = sc.nextLine();
        s+=startingTime+",";

        System.out.print("Enter number plate (needs to be unique): ");
        numberPlate = sc.nextLine();
        s+=numberPlate+",";

        System.out.print("Enter phone number: ");
        phoneNumber = sc.nextLine();
        s+=phoneNumber+",";

        System.out.print("Enter row numbers: ");
        row = sc.nextInt();
        s+=row+",";

        System.out.print("Enter column numbers: ");
        col = sc.nextInt();
        s+=col+",";

        fileHandler.appendToFile(s);
    }

    public void savedetails(String s) throws IOException {

        fileHandler.appendToFile(s);
        System.out.println("Added successfully!");
    }
}
