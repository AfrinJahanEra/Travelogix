package Traveler.Checklist_NoteKeeping.NoteKeeping;

import Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote.VoiceNoteDashBoard;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NotewritingDashboard;
import java.util.Scanner;


public class NoteKeepingDashboard {
     public void displayChecklist() {
        
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("[1] Voice Note");
            System.out.println("[2] Written Note");
            System.out.println("[3] Go back (Exit)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    VoiceNoteDashBoard voiceNoteDashBoard = new VoiceNoteDashBoard();
                    voiceNoteDashBoard.voiceNoteDashBoard();
                case 2:
                    NotewritingDashboard notewritingDashboard = new NotewritingDashboard();
                    notewritingDashboard.notewritingDashboard();
                    break;
                case 3:
                    System.out.println("Exiting the checklist manager. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        }
    }
}
