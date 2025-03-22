
package Transport.Bus;

import Utilities.FileManager.File.FileHandler;
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

    private final FileHandler fileHandler;

    public AddBus(String filePath) {
        this.fileHandler = new FileHandler(filePath);
    }

    public void inputBusDetails() throws IOException {
        StringBuilder s = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        System.out.printf("%-40s: ", "Enter Bus Name");
        busName = sc.nextLine();
        s.append(busName).append(",");

        System.out.printf("%-40s: ", "Enter Departure Location");
        startingLocation = sc.nextLine();
        s.append(startingLocation).append(",");

        System.out.printf("%-40s: ", "Enter Destination");
        endingLocation = sc.nextLine();
        s.append(endingLocation).append(",");

        while (true) {
            System.out.printf("%-40s: ", "Enter Departure Time in 24-hour format (HH:MM)");
            startingTime = sc.nextLine();
            if (startingTime.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d$")) {
                s.append(startingTime).append(",");
                break;
            } else {
                System.out.println("Invalid format. Please enter time in 24-hour HH:MM format.");
            }
        }

        while (true) {
            System.out.printf("%-40s: ", "Enter Number Plate (unique)");
            numberPlate = sc.nextLine();
            if (isNumberPlateUnique(numberPlate)) {
                s.append(numberPlate).append(",");
                break;
            } else {
                System.out.println("This number plate already exists! Enter a unique number plate.");
            }
        }

        while (true) {
            System.out.printf("%-40s: ", "Enter Contact Number (+880-XXXXXXXXXX)");
            phoneNumber = sc.nextLine();
            if (phoneNumber.matches("^\\+880-\\d{10}$")) {
                s.append(phoneNumber).append(",");
                break;
            } else {
                System.out.println("Invalid Contact Number Format. Please enter in +880-XXXXXXXXXX format.");
            }
        }

        while (true) {
            System.out.printf("%-40s: ", "Enter Row numbers");
            try {
                row = sc.nextInt();
                if (row > 0) {
                    s.append(row).append(",");
                    break;
                } else {
                    System.out.println("Row number must be a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer for the row number.");
                sc.next(); // Clear the invalid input
            }
        }

        while (true) {
            System.out.printf("%-40s: ", "Enter Column numbers");
            try {
                col = sc.nextInt();
                if (col > 0) {
                    s.append(col).append(",");
                    break;
                } else {
                    System.out.println("Column number must be a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer for the column number.");
                sc.next(); // Clear the invalid input
            }
        }

        savedetails(s.toString());
    }

    private boolean isNumberPlateUnique(String numberPlate) throws IOException {
        String content = fileHandler.readFromFile();
        String[] lines = content.split("\n");

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length > 4 && parts[4].trim().equalsIgnoreCase(numberPlate)) {
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
