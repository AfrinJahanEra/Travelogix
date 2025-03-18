package Traveler.Checklist_NoteKeeping.CheckList;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckListdashBoard{

    Scanner scanner = new Scanner(System.in);
    public void displayChecklist() {

        Checklist checklist = new Checklist();
        AddItems addItems = new AddItems();
        MarkPacked markPacked = new MarkPacked();
        DeleteItem deleteItem = new DeleteItem();  // Add this line
        DisplayCheckList displayCheckList = new DisplayCheckList();
        
        
        while (true) {

            waitForEnterKey();
            clearTerminal();

            System.out.println("\n                                              ╔══════════════════════════════════════════╗");
            System.out.println("                                              ║            CHECKLIST OF ITEMS            ║");
            System.out.println("                                              ╠══════════════════════════════════════════╣");
            System.out.println("                                              ║                                          ║");
            System.out.println("                                              ║    [1] Add checklist items               ║");
            System.out.println("                                              ║    [2] Mark packed items                 ║");
            System.out.println("                                              ║    [3] Delete checklist item             ║");
            System.out.println("                                              ║    [4] Go back (Exit)                    ║");
            System.out.println("                                              ╚══════════════════════════════════════════╝");

            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addItems.addItems(checklist);  
                    break;
                case 2:
                    markPacked.markPacked(checklist);  
                    break;
                case 3:
                    deleteItem.deleteChecklistItem(checklist);  // Add this case
                    break;
                case 4:
                    System.out.println("Exiting the checklist manager. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
            
            displayCheckList.displayChecklist(checklist);  
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine(); // Clear invalid input
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
