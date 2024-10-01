package Transport_Agency;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TransportAgency agency = new TransportAgency();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Create Bus Transport");
        System.out.println("2. Search Bus by Name");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            agency.create_transport();
        } else if (choice == 2) {
            System.out.print("Enter the bus name to search: ");
            String busNameToSearch = scanner.nextLine();
            agency.search_bus_by_name(busNameToSearch);
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
