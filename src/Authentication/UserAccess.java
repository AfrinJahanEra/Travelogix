package Authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserAccess {

    private static final int CONSOLE_WIDTH = 120;

    Login in = new Login();
    SignUp out = new SignUp();

    public void start() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);

        // Welcome Message
        printTitle("TRAVELOGIX");

        while (true) {

               
                    System.out.println("\n╔════════════════════════════════════╗");
                    System.out.println("║           Main Menu                ║");
                    System.out.println("╠════════════════════════════════════╣");
                    System.out.println("║           1. Login                 ║");
                    System.out.println("║           2. Sign Up               ║");
                    System.out.println("║           0. Exit                  ║");
                    System.out.println("╚════════════════════════════════════╝");
                
                
            try {
                System.err.println("Enter your choice:");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        System.out.println("\nLogging you in...");
                        in.logIn(); // Call login method
                        break;
                    case 2:
                        System.out.println("\nStarting Sign Up...");
                        out.signUp(); // Call sign up method
                        break;
                    case 0:
                        System.out.println("\nThank you for using Travelogix. Goodbye!");
                        scanner.close();
                        System.exit(0); // Exit the program
                        break;
                    default:
                        System.out.println("\nInvalid option! Please enter 1, 2, or 0.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private void printTitle(String message) {
        clearTerminal(); // Clear the terminal before displaying the title
    
        // Define small ASCII art for each letter
        String[][] alphabet = new String[26][];
        alphabet['T' - 'A'] = new String[]{
                "███████",
                "   █   ",
                "   █   ",
                "   █   ",
                "   █   "
        };
        alphabet['R' - 'A'] = new String[]{
                "██████ ",
                "█    █ ",
                "█████  ",
                "█  █   ",
                "█   █  "
        };
        alphabet['A' - 'A'] = new String[]{
                "  ███  ",
                " █   █ ",
                "██████ ",
                "█    █ ",
                "█    █ "
        };
        alphabet['V' - 'A'] = new String[]{
                "█    █ ",
                "█    █ ",
                " █  █  ",
                "  ██   ",
                "  ██   "
        };
        alphabet['E' - 'A'] = new String[]{
                "██████ ",
                "█      ",
                "█████  ",
                "█      ",
                "██████ "
        };
        alphabet['L' - 'A'] = new String[]{
                "█      ",
                "█      ",
                "█      ",
                "█      ",
                "█████  "
        };
        alphabet['O' - 'A'] = new String[]{
                " ████  ",
                "█    █ ",
                "█    █ ",
                "█    █ ",
                " ████  "
        };
        alphabet['G' - 'A'] = new String[]{
                " ████  ",
                "█      ",
                "█  ███ ",
                "█    █ ",
                " ████  "
        };
        alphabet['I' - 'A'] = new String[]{
                " ████  ",
                "   █   ",
                "   █   ",
                "   █   ",
                " ████  "
        };
        alphabet['X' - 'A'] = new String[]{
                "█    █ ",
                " █  █  ",
                "  ██   ",
                " █  █  ",
                "█    █ "
        };
    
        // Prepare ASCII art for the input message
        String[] rows = new String[5]; // Each letter has 5 rows
        for (int i = 0; i < rows.length; i++) {
            rows[i] = ""; // Initialize rows
        }
    
        for (char c : message.toUpperCase().toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                String[] letterArt = alphabet[c - 'A'];
                for (int i = 0; i < rows.length; i++) {
                    rows[i] += letterArt[i] + "  "; // Add spacing between letters
                }
            }
        }

        int terminalWidth = 120;
    
        for (String row : rows) {
            int padding = (terminalWidth - row.length()) / 2;
            System.out.println(" ".repeat(Math.max(0, padding)) + row);
        }
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
