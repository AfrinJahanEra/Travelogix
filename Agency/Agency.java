package Agency;

import Bus.Addbus;
import Bus.Buslist;
import Bus.Viewdetails;
import Bus.Editdetails;
import Bus.Deletedetails;
import Seat.Addseat;

import java.util.Scanner;

public class Agency {
    Buslist buslist = new Buslist();
    Scanner sc = new Scanner(System.in);

    public void agency() {
        int choice;

        do {
            displayMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addBus();
                    break;
                case 2:
                    viewBusDetails();
                    break;
                case 3:
                    editBusDetails();
                    break;
                case 4:
                    deleteBusDetails();
                    break;
                case 5:
                    bookSeat(); // Book seat functionality
                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    private void displayMenu() {
        System.out.println("1. Add Bus");
        System.out.println("2. View Details");
        System.out.println("3. Edit Details");
        System.out.println("4. Delete Details");
        System.out.println("5. Book Seat");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addBus() {
        Addbus addbus = new Addbus();
        addbus.inputBusDetails();
        addbus.saveToFile();
    }

    private void viewBusDetails() {
        buslist.list();
        System.out.print("Enter the index of the bus: ");
        int busIndex = sc.nextInt();
        Viewdetails viewdetails = new Viewdetails();
        viewdetails.showdetails(busIndex);
    }

    private void editBusDetails() {
        buslist.list();
        System.out.print("Enter the index of the bus: ");
        int busIndex = sc.nextInt();
        Editdetails editdetails = new Editdetails();
        editdetails.editdetails(busIndex);
    }

    private void deleteBusDetails() {
        buslist.list();
        System.out.print("Enter the index of the bus: ");
        int busIndex = sc.nextInt();
        Deletedetails deletedetails = new Deletedetails();
        deletedetails.deletedetails(busIndex);
    }

    private void bookSeat() {
        buslist.list();
        System.out.print("Enter the index of the bus: ");
        int busIndex = sc.nextInt();

        Addseat addseat = new Addseat();
        addseat.bookingDetails(busIndex);  // Displays the seat matrix, allows booking, and saves to file
    }

}
