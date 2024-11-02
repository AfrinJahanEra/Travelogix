package Source.Transport_Agency;
import Source.File.*;
import Source.Bus.*;
import Source.Seat.*;

import java.io.IOException;
import java.util.Scanner;

public class Dashbaord {
    private Scanner sc = new Scanner(System.in);
    private int choice;
    private FileHandler fileHandler = new FileHandler("bus.txt");

    public void dashboard() throws IOException {
        do {
            displayMenu();
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBus();
                case 2 -> viewDetails();
                case 3 -> busOptions();
                case 4 -> System.out.println("Exiting.....");
                default -> System.out.println("Invalid input");
            }
        } while (choice != 4);
    }

    private void displayMenu() {
        System.out.println("1. Add Bus");
        System.out.println("2. View Bus List");
        System.out.println("3. View Bus options");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addBus() throws IOException {
        new AddBus("bus.txt").inputBusDetails();
    }

    private void busOptions() throws IOException {
        System.out.println("View a bus details(v) / Edit bus details(e) / Book a seat(b) / Delete bus (d)");
        String s = sc.next().trim();

        switch (s) {
            case "v" -> new ViewBusDetails("bus.txt").viewBusDetails();
            case "e" -> new EditBus("bus.txt").editBusDetails();
            case "b" -> new SeatBooking("bus.txt").initiateBooking();
            case "d" -> new DeleteBus("bus.txt").deleteBus();
            default -> System.out.println("Invalid option. Please choose 'e', 'b', or 'd'.");
        }
    }

    private void viewDetails() {
        ViewBusList viewBusList = new ViewBusList("bus.txt");
        System.out.println("View bus with number plate (n) / starting location (s) / ending location (e)");
        String s = sc.next().trim();

        switch (s) {
            case "n" -> viewBusList.list(4);
            case "s" -> viewBusList.list(1);
            case "e" -> viewBusList.list(2);
            default -> System.out.println("Invalid input! Please enter 'n', 's', or 'e'.");
        }
    }
}
