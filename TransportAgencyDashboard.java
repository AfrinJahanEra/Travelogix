import java.util.Scanner;

public class TransportAgencyDashboard {
    public void dashboard(TransportAgency agency, TransportAgencyFunctionalities functionalities, User user) {
        Scanner sc = new Scanner(System.in);
        boolean continueDashboard = true;

        while (continueDashboard) {
            System.out.println("Choose any of these options: ");
            System.out.println("1. View Account");
            System.out.println("2. Book a Seat");
            System.out.println("3. Edit Seat no.");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    functionalities.viewAccount(user);
                    break;
                case 2:
                    functionalities.bookSeat(agency, user);
                    break;
                case 3:
                    functionalities.editBooking(user, agency);
                    break;
                case 4:
                    continueDashboard = false;

                    break;
                default:
                    System.out.println("Invalid choice! Please choose one from these options.");
                    break;
            }
        }
    }
}
