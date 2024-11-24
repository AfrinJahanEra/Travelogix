package Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote;

import java.util.Scanner;

public class NotewritingDashboard {

    public void notewritingDashboard() {
        NoteManager noteManager = new NoteManager();
        NoteWriter noteWriter = new NoteWriter(noteManager);
        NoteReader noteReader = new NoteReader(noteManager);
        NoteDeleter noteDeleter = new NoteDeleter(noteManager); 

        Scanner scanner = new Scanner(System.in);

        while (true) {
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
}
