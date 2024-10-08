package Agency;
import Bus.*;

import java.util.Scanner;
import Seat.*;

public class Agency {

    public void dashboard() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Travelogix Agency Dashboard ---");
            System.out.println("1. Add Bus");
            System.out.println("2. View Bus");
            System.out.println("3. Delete bus");
            System.out.println("4. Edit bus");
            System.out.println("5. Show seat");
            System.out.println("6. Edit seat");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    AddBus addBus = new AddBus();
                    addBus.inputBusDetails();
                    addBus.displayBusDetails();
                    addBus.saveToFile();
                    System.out.println("Bus added successfully!");
                    break;
                case 2:
                    ViewBus viewBus = new ViewBus();
                    viewBus.displayAllBuses();
                    break;

                case 3:
                    DeleteBus deleteBus = new DeleteBus();
                    deleteBus.deleteBus();
                    break;

                case 4:
                    EditBus editBus = new EditBus();
                    editBus.editBus();
                    break;

                case 5:
                    ShowSeat showSeat = new ShowSeat();
                    showSeat.displaySeatMatrix();
                    break;

                case 6:
                    EditSeat editSeat = new EditSeat();
                    editSeat.bookSeats();
                    break;

                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 7);
    }
}
