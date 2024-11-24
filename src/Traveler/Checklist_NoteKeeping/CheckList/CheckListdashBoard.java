package Traveler.Checklist_NoteKeeping.CheckList;

import java.util.Scanner;

public class CheckListdashBoard{


    public void displayChecklist() {
        Checklist checklist = new Checklist();
        AddItems addItems = new AddItems();
        MarkPacked markPacked = new MarkPacked();
        DeleteItem deleteItem = new DeleteItem();  // Add this line
        DisplayCheckList displayCheckList = new DisplayCheckList();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║            CHECKLIST OF ITEMS            ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] Add checklist items               ║");
            System.out.println("║    [2] Mark packed items                 ║");
            System.out.println("║    [3] Delete checklist item             ║");
            System.out.println("║    [4] Go back (Exit)                    ║");
            System.out.println("╚══════════════════════════════════════════╝");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

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
}
