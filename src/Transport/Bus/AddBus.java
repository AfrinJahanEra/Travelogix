package Source.Bus;
import Source.File.*;
import java.io.IOException;
import java.util.InputMismatchException;
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

        String s = "";
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bus name: ");
        busName = sc.nextLine();
        s += busName + ",";

        System.out.print("Enter starting location: ");
        startingLocation = sc.nextLine();
        s += startingLocation + ",";

        System.out.print("Enter ending location: ");
        endingLocation = sc.nextLine();
        s += endingLocation + ",";

        while (true) {
            System.out.print("Enter starting time in 24-hour HH:MM format: ");
            startingTime = sc.nextLine();
            if (startingTime.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d$")) {
                s += startingTime + ",";
                break;
            } else {
                System.out.println("Invalid format. Please enter time in 24-hour HH:MM format.");
            }
        }


        while (true) {
            System.out.print("Enter number plate (needs to be unique): ");
            numberPlate = sc.nextLine();
            if (isNumberPlateUnique(numberPlate)) {
                s += numberPlate + ",";
                break;
            } else {
                System.out.println("This number plate already exists! Enter a unique number plate.");
            }
        }

        while (true) {
            System.out.print("Enter phone number in +880-XXXXXXXXXX format: ");
            phoneNumber = sc.nextLine();
            if (phoneNumber.matches("^\\+880-\\d{10}$")) {
                s += phoneNumber + ",";
                break;
            } else {
                System.out.println("Invalid phone number format. Please enter in +880-XXXXXXXXXX format.");
            }
        }


        while (true) {
            System.out.print("Enter row numbers: ");
            try {
                row = sc.nextInt();
                s += row + ",";
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer for the row number.");
                sc.next();
            }
        }

        while (true) {
            System.out.print("Enter column numbers: ");
            try {
                col = sc.nextInt();
                s += col + ",";
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer for the column number.");
                sc.next();
            }
        }

        savedetails(s);
    }

    private boolean isNumberPlateUnique(String numberPlate) throws IOException {
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length > 4 && parts[4].trim().equals(numberPlate)) {
                return false;
            }
        }
        return true;
    }

    public void savedetails(String s) throws IOException {
        fileHandler.appendToFile(s);
        System.out.println("Added successfully!");
    }
}
