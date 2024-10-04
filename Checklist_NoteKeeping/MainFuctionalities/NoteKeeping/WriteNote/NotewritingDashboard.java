package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.WriteNote;

import java.util.Scanner;

public class NotewritingDashboard {
    public void notewritingDashboard(){
    NoteManager noteManager = new NoteManager();
        NoteWriter noteWriter = new NoteWriter(noteManager);
        NoteReader noteReader = new NoteReader(noteManager);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Note");
            System.out.println("2. Show Note");
            System.out.println("3. Exit");
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
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

