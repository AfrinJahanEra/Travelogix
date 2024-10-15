package Agency;

import Bus.*;
import Seat.*;
import ByList.*;

import java.util.Scanner;

public class Agency {
    Buslist buslist = new Buslist();
    Scanner sc = new Scanner(System.in);

    public void agency() {
        int choice;

        do {
            displayMenu();
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character after nextInt()

            switch (choice) {
                case 1:
                    addBus();
                    break;
                case 2:
                    viewBusDetails();
                    break;
                case 3:
                    viewBusDetailsbylist();
                    break;
                case 4:
                    editBusDetails();
                    break;
                case 5:
                    editBusDetailsbylist();
                    break;
                case 6:
                    deleteBusDetails();
                    break;
                case 7:
                    deleteBusDetailsbylist();
                    break;
                case 8:
                    bookSeats(); // Book seat functionality
                    break;
                case 9:
                    addseatbylist();
                    break;
                case 10:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 10);
    }


    private void displayMenu() {
        System.out.println("1. Add Bus");
        System.out.println("2. View Details by search");
        System.out.println("3. View Details by list");
        System.out.println("4. Edit Details by search");
        System.out.println("5. Edit Details by list");
        System.out.println("6. Delete Details by search");
        System.out.println("7. Delete Details by list");
        System.out.println("8. Book Seat by search");
        System.out.println("9. Book Seat by list");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addBus() {
        Addbus addbus = new Addbus();
        addbus.inputBusDetails();
        addbus.saveToFile();
    }

    private void viewBusDetails() {
        // Ask user for bus name, starting location, and ending location instead of index
        System.out.print("Enter the bus name: ");
        String busName = sc.nextLine();
        System.out.print("Enter the starting location: ");
        String startLocation = sc.nextLine();
        System.out.print("Enter the ending location: ");
        String endLocation = sc.nextLine();

        Viewdetails viewdetails = new Viewdetails();
        viewdetails.showdetails(busName, startLocation, endLocation);
    }

    private void viewBusDetailsbylist()
    {
        buslist.list();
        System.out.print("Enter the index of the bus: ");
        int busIndex = sc.nextInt();
        Viewdetailsbylist viewdetailsbylist = new Viewdetailsbylist();
        viewdetailsbylist.showdetails(busIndex);
    }

    private void editBusDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the bus name: ");
        String busName = sc.nextLine();

        System.out.print("Enter the starting location: ");
        String startingLocation = sc.nextLine();

        System.out.print("Enter the ending location: ");
        String endingLocation = sc.nextLine();

        Editdetails editdetails = new Editdetails();
        editdetails.editDetails(busName, startingLocation, endingLocation);
    }

    private void editBusDetailsbylist()
    {
        buslist.list();
        System.out.print("Enter the index of the bus: ");
        int busIndex = sc.nextInt();
        Editdetailsbylist editdetailsbylist = new Editdetailsbylist();
        editdetailsbylist.editdetails(busIndex);
    }


    private void deleteBusDetails() {
        System.out.print("Enter the bus name: ");
        String busName = sc.nextLine();

        System.out.print("Enter the starting location: ");
        String startingLocation = sc.nextLine();

        System.out.print("Enter the ending location: ");
        String endingLocation = sc.nextLine();

        Deletedetails deletedetails = new Deletedetails();
        deletedetails.deleteDetails(busName, startingLocation, endingLocation);
    }

    private void deleteBusDetailsbylist()
    {
        buslist.list();
        System.out.print("Enter the index of the bus: ");
        int busIndex = sc.nextInt();
        Deletedetailsbylist deletedetailsbylist = new Deletedetailsbylist();
        deletedetailsbylist.deletedetails(busIndex);
    }


    private void bookSeats() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the bus name: ");
        String busName = sc.nextLine();

        System.out.print("Enter the starting location: ");
        String startingLocation = sc.nextLine();

        System.out.print("Enter the ending location: ");
        String endingLocation = sc.nextLine();

        Addseat addSeat = new Addseat();
        addSeat.bookingDetails(busName, startingLocation, endingLocation);
    }

    private void addseatbylist()
    {
        buslist.list();
        System.out.print("Enter the index of the bus: ");
        int busIndex = sc.nextInt();

        Addseatbylist addseatbylist = new Addseatbylist();
        addseatbylist.bookingDetails(busIndex);
    }


}
