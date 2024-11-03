package Transport;

import Transport.Bus.AddBus;
import Transport.Bus.DeleteBus;
import Transport.Bus.EditBus;
import Transport.Bus.ViewBusDetails;
import Transport.Bus.ViewBusList;
import Transport.Seat.SeatBooking;
import Utilities.FileManager.File.FileHandler;

import java.io.IOException;
import java.util.Scanner;

public class TransportDashboard{
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
            case "v" -> new ViewBusDetails("bus.txt").numberPlate();
            case "e" -> new EditBus("bus.txt").numberPlate();
            case "b" -> new SeatBooking("bus.txt").initiateBooking();
            case "d" -> new DeleteBus("bus.txt").numberPlate();
            default -> System.out.println("Invalid option. Please choose 'v', 'e', 'b', or 'd'.");
        }
    }

    private void viewDetails() {
        ViewBusList viewBusList = new ViewBusList("bus.txt");
        System.out.println("View bus with number plate (n) / starting location (s) / ending location (e)");
        String s = sc.next().trim();

        switch (s) {
            case "n" -> System.out.println(viewBusList.list(4));
            case "s" -> System.out.println(viewBusList.list(1));
            case "e" -> System.out.println(viewBusList.list(2));
            default -> System.out.println("Invalid input! Please enter 'n', 's', or 'e'.");
        }
    }
}