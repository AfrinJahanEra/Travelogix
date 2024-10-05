package Transport_Agency;

import java.io.*;
import java.util.Scanner;

public class TransportAgency {
    public void menu() {
        Create_Bus createBus = new Create_Bus();
        Edit_Bus editBus = new Edit_Bus();
        Search_Bus searchBus = new Search_Bus();
        Delete_Bus deleteBus = new Delete_Bus();
        Show_Bus showBus = new Show_Bus();
        Edit_Seat_Availability editSeatAvailability = new Edit_Seat_Availability();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Transport Agency System!");
            System.out.println("1. Create Bus Transport");
            System.out.println("2. Edit Bus Transport");
            System.out.println("3. Show Bus Details (Search by Name)");
            System.out.println("4. Delete Bus details(Search by name)");
            System.out.println("5. Show bus details from a specific location");
            System.out.println("6. Edit seat availability");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    createBus.create_bus();
                    break;

                case 2:

                    System.out.print("Enter the bus name to edit: ");
                    String busNameToEdit = scanner.nextLine();

                    System.out.println("What would you like to edit?");
                    System.out.println("1. Edit Bus Name");
                    System.out.println("2. Edit Starting Location");
                    System.out.println("3. Edit Ending Location");
                    System.out.println("4. Edit Cost Per Seat");

                    int editChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (editChoice) {
                        case 1:

                            System.out.print("Enter the new bus name: ");
                            String newBusName = scanner.nextLine();
                            editBus.edit_bus_name(busNameToEdit, newBusName);
                            break;


                        case 2:

                            System.out.print("Enter the new starting location: ");
                            String newStartLocation = scanner.nextLine();
                            editBus.edit_starting_location(busNameToEdit, newStartLocation);
                            break;

                        case 3:

                            System.out.print("Enter the new ending location: ");
                            String newEndLocation = scanner.nextLine();
                            editBus.edit_ending_location(busNameToEdit, newEndLocation);
                            break;

                        case 4:

                            System.out.print("Enter the new cost per seat: ");
                            double newCostPerSeat = scanner.nextDouble();
                            editBus.edit_cost_per_seat(busNameToEdit, newCostPerSeat);
                            break;

                        default:
                            System.out.println("Invalid edit option.");
                            break;
                    }
                    break;

                case 3:

                    System.out.print("Enter the bus name to search: ");
                    String busNameToSearch = scanner.nextLine();
                    searchBus.search_bus_by_name(busNameToSearch);
                    break;

                case 4:
                    System.out.println("Enter the bus name to delete: ");
                    String busNameToDelete = scanner.nextLine();
                    deleteBus.delete_bus_by_name(busNameToDelete);


                case 5:
                    System.out.println("Enter starting location of bus: ");
                    String startingLocation = scanner.nextLine();
                    System.out.println("Enter ending location of bus: ");
                    String endingLocation = scanner.nextLine();
                    showBus.show_buses_by_route(startingLocation,endingLocation);

                case 6:
                    System.out.println("Enter bus name to search: ");
                    String busName = scanner.nextLine();
                    editSeatAvailability.edit_seat_availability(busName);


                case 7:

                    System.out.println("Exiting the system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println("\n");
        }
    }
}

