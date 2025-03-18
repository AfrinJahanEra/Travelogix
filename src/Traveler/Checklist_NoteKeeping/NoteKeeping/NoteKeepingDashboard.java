package Traveler.Checklist_NoteKeeping.NoteKeeping;

import Traveler.Checklist_NoteKeeping.CheckList.CheckListdashBoard;
import Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote.VoiceNoteDashBoard;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NotewritingDashboard;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NoteKeepingDashboard {

    Scanner scanner=new Scanner(System.in);
    public void displayChecklist() {

        while (true) {

            waitForEnterKey();
            clearTerminal();

            System.out.println("\n                                          ╔══════════════════════════════════════════╗");
            System.out.println("                                          ║                   NOTES                  ║");
            System.out.println("                                          ╠══════════════════════════════════════════╣");
            System.out.println("                                          ║                                          ║");
            System.out.println("                                          ║    [1] Voice Note                        ║");
            System.out.println("                                          ║    [2] Written Note                      ║");
            System.out.println("                                          ║    [3] CheckList                         ║");
            System.out.println("                                          ║    [4] Go back (Exit)                    ║");
            System.out.println("                                          ╚══════════════════════════════════════════╝");

            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    VoiceNoteDashBoard voiceNoteDashBoard = new VoiceNoteDashBoard();
                    voiceNoteDashBoard.voiceNoteDashBoard();
                    break;
                case 2:
                    NotewritingDashboard notewritingDashboard = new NotewritingDashboard();
                    notewritingDashboard.notewritingDashboard();
                    break;
                case 3:
                    CheckListdashBoard checklistDashboard = new CheckListdashBoard();
                    checklistDashboard.displayChecklist();
                    break;
                case 4:
                    System.out.println("Exiting the note-keeping dashboard. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine(); 
            }
        }
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

