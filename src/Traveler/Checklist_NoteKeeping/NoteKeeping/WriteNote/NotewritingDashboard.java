package Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote;

import java.io.IOException;
import java.util.Scanner;

public class NotewritingDashboard {

    public void notewritingDashboard() {
        
        NoteManager noteManager = new NoteManager();
        NoteWriter noteWriter = new NoteWriter(noteManager);
        NoteReader noteReader = new NoteReader(noteManager);
        NoteDeleter noteDeleter = new NoteDeleter(noteManager); 

        Scanner scanner = new Scanner(System.in);

        while (true) {

            waitForEnterKey();
            clearTerminal();

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║                WRITE NOTES               ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] Add Note                          ║");
            System.out.println("║    [2] Show Note                         ║");
            System.out.println("║    [3] Delete Note                       ║");
            System.out.println("║    [4] Go back (Exit)                    ║");
            System.out.println("╚══════════════════════════════════════════╝");
            
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    noteWriter.writeAndSaveNote();
                    break;
                case 2:
                    noteReader.showAndReadNote();
                    break;
                case 3:
                    noteDeleter.deleteNote();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
