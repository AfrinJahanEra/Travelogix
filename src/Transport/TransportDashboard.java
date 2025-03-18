package Transport;

import Authentication.DeleteAccount;
import Authentication.UserAccess;
import Transport.Bus.AddBus;
import Transport.Bus.DeleteBus;
import Transport.Bus.EditBus;
import Transport.Bus.ViewBusDetails;
import Transport.Bus.ViewBusList;
import Transport.Seat.SeatBooking;
import Utilities.FileManager.File.FileHandler;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TransportDashboard {
    private Scanner sc = new Scanner(System.in);
    private int choice;
    private FileHandler fileHandler = new FileHandler("src\\TXT_Files\\bus.txt");

    public boolean dashboard() throws IOException, NoSuchAlgorithmException {
        boolean isRunning = true;

        while (isRunning) {

            waitForEnterKey();
            clearTerminal();
            
            displayMainMenu();
            choice = getValidIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1 -> addBus();
                case 2 -> viewBusList();
                case 3 -> busOptions();
                case 4 -> deleteAccount();
                case 5 -> {
                    System.out.println("Logging out...");
                    isRunning = false;
                    UserAccess userAccess = new UserAccess();
                    userAccess.start();
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
        return false;
    }

    private void displayMainMenu() {
        clearTerminal();

        System.out.println("                                              ╔════════════════════════════════════════╗");
        System.out.println("                                              ║            TRANSPORT DASHBOARD         ║");
        System.out.println("                                              ╠════════════════════════════════════════╣");
        System.out.println("                                              ║                                        ║");
        System.out.println("                                              ║    [1] Add Bus                         ║");
        System.out.println("                                              ║    [2] View Bus List                   ║");
        System.out.println("                                              ║    [3] Bus Options                     ║");
        System.out.println("                                              ║    [4] Delete Account                  ║");
        System.out.println("                                              ║    [5] Log Out                         ║");
        System.out.println("                                              ║                                        ║");
        System.out.println("                                              ╚════════════════════════════════════════╝");
    }

    private void addBus() throws IOException {
        clearTerminal();
        System.out.println("\n");
        System.out.println("               Adding a New Bus                ");
        System.out.println("═════════════════════════════════════════════\n");
        new AddBus("src\\TXT_Files\\bus.txt").inputBusDetails();
    }

    private void viewBusList() {
        clearTerminal();

        ViewBusList viewBusList = new ViewBusList("src\\TXT_Files\\bus.txt");

        System.out.println("\n                                              ╔══════════════════════════════════════════╗");
        System.out.println("                                              ║               VIEW BUS LIST              ║");
        System.out.println("                                              ╠══════════════════════════════════════════╣");
        System.out.println("                                              ║                                          ║");
        System.out.println("                                              ║    View by:                              ║");
        System.out.println("                                              ║    [n] Number Plate                      ║");
        System.out.println("                                              ║    [s] Starting Location                 ║");
        System.out.println("                                              ║    [e] Ending Location                   ║");
        System.out.println("                                              ║                                          ║");
        System.out.println("                                              ╚══════════════════════════════════════════╝");

        
        String input = getValidStringInput("Enter your choice: ", "[nNsSeE]");

        switch (input.toLowerCase()) {
            case "n" -> System.out.println(viewBusList.list(4));
            case "s" -> System.out.println(viewBusList.list(1));
            case "e" -> System.out.println(viewBusList.list(2));
            default -> System.out.println("Invalid input! Please enter 'n', 's', or 'e'.");
        }
    }

    private void busOptions() throws IOException {

        clearTerminal();

        System.out.println("\n                                              ╔══════════════════════════════════════════╗");
        System.out.println("                                              ║               BUS OPTIONS                ║");
        System.out.println("                                              ╠══════════════════════════════════════════╣");
        System.out.println("                                              ║                                          ║");
        System.out.println("                                              ║    [v] View Bus Details                  ║");
        System.out.println("                                              ║    [e] Edit Bus Details                  ║");
        System.out.println("                                              ║    [b] Book a Seat                       ║");
        System.out.println("                                              ║    [d] Delete Bus                        ║");
        System.out.println("                                              ║                                          ║");
        System.out.println("                                              ╚══════════════════════════════════════════╝");

        String s = getValidStringInput("Enter your choice: ", "[vVeEbBdD]");

        switch (s.toLowerCase()) {
            case "v" -> {
                System.out.println("\n"); 
                System.out.println("             Viewing Bus Details             ");
                System.out.println("═════════════════════════════════════════════\n");
                new ViewBusDetails("src\\TXT_Files\\bus.txt").numberPlate();
            }
            case "e" -> {
                System.out.println("\n");
                System.out.println("             Editing Bus Details             ");
                System.out.println("═════════════════════════════════════════════\n");
                new EditBus("src\\TXT_Files\\bus.txt").numberPlate();
            }
            case "b" -> {
                System.out.println("\n");
                System.out.println("                Booking a Seat                  ");
                System.out.println("═════════════════════════════════════════════\n");
                new SeatBooking("src\\TXT_Files\\bus.txt").initiateBooking();
            }
            case "d" -> {
                System.out.println("\n");
                System.out.println("                  Deleting Bus                   ");
                System.out.println("═════════════════════════════════════════════\n");
                new DeleteBus("src\\TXT_Files\\bus.txt").numberPlate();
            }
            default -> {
                System.out.println("\n");
                System.out.println("Invalid option. Please choose  ");
                System.out.println("'v', 'e', 'b', or 'd'.          ");
            }
        }
    }

    private void deleteAccount() throws NoSuchAlgorithmException, IOException {

        if (new DeleteAccount().deleteAccount()) {
            System.out.println("\n");
            System.out.println("    Account deleted successfully.   ");
            System.out.println("═════════════════════════════════════════════\n");
        } else {
            System.out.println("\n");
            System.out.println("Account deletion cancelled or ");
            System.out.println("failed.                         ");
        }
    }

        private int getValidIntegerInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Clear invalid input
            }
        }
    }

    private String getValidStringInput(String prompt, String regex) {
        while (true) {
            System.out.print(prompt);
            String input = sc.next().trim();
            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void waitForEnterKey() {
        System.out.println("\nPress ENTER to continue...");
        Scanner enterScanner = new Scanner(System.in);
        enterScanner.nextLine(); // Waits for the ENTER key press
    }

    private void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to clear terminal.");
        }
    }
}
