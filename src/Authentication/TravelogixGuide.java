package Authentication;

import java.io.IOException;
import java.util.Scanner;

public class TravelogixGuide {
    public void print() {

        waitForEnterKey();

        clearTerminal();
        // Print ASCII Art Logo
        printAsciiArt();

        // Display How-To-Use Guide
        printUserGuide();
    }

    // Method to Print ASCII Art
    public static void printAsciiArt() {

        System.out.println("                                               =============================================");
        System.out.println("                                                 ████████╗  ██╗   ██╗ ██  █████╗   ██████╗ ");
        System.out.println("                                                 ██╔═════╝  ██║   ██║ ██║ ██╔══██╗ ██╔═══╝ ");
        System.out.println("                                                 ██║  ███╗  ██║   ██║ ██║ ██║  ██║ █████╗   ");
        System.out.println("                                                 ██║   ██║  ██║   ██║ ██║ ██║  ██║ ██╔══╝   ");
        System.out.println("                                                 ╚██████╔╝  ╚██████╝  ██║ ██████╔╝ ███████╗");
        System.out.println("                                                 ╚═════╝     ╚════╝   ╚═╝ ╚═════╝  ╚══════╝ ");
        System.out.println("                                               =============================================");

        System.out.println("                                                        Welcome to Travelogix!");
        System.out.println("                                               Simplify your travel planning experience.\n");
    }

    // Method to Print How-To-Use Guide
    public static void printUserGuide() {
        System.out.println("                                           =============== HOW TO USE TRAVELOGIX ===============");
        System.out.println("                                       ");
        System.out.println("                                       1.Create an account if You don't have any other wise press login");
        System.out.println("                                       ");
        System.out.println("                                                      2.Enter your role when prompted: ");
        System.out.println("                                           - Choose 'traveler' for a Traveler Dashboard");
        System.out.println("                                           - Choose 'transport' for a Transport Agency Dashboard");
        System.out.println("                                           - Choose 'admin' for an Admin Dashboard");
        System.out.println("                                       ");
        System.out.println("                                       3.The system will display your role-specific dashboard.");
        System.out.println("                                       ");
        System.out.println("                                       4.Choose an option from the menu and follow the instructions .");
        System.out.println("                                       ");
        System.out.println("                                              5.To exit, close the program or press 'exit' key.");
        System.out.println("                                           =====================================================");
    }

    private void waitForEnterKey() {
        System.out.println("\nPress ENTER to continue...");
        Scanner enterScanner = new Scanner(System.in);
        enterScanner.nextLine();
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
