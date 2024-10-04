package Checklist_NoteKeeping.MainFuctionalities.CheckList;

import java.util.Scanner;

public class CheckListdashBoard {

    public void displayChecklist() {
        Checklist checklist = new Checklist();
        AddItems addItems = new AddItems();
        MarkPacked markPacked = new MarkPacked();
        DisplayCheckList displayCheckList = new DisplayCheckList();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Add checklist items");
            System.out.println("2. Mark packed items");
            System.out.println("3. Go back (Exit)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItems.addItems(checklist);  // Add items
                    break;
                case 2:
                    markPacked.markPacked(checklist);  // Mark items as packed
                    break;
                case 3:
                    System.out.println("Exiting the checklist manager. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
            
            displayCheckList.displayChecklist(checklist);  // Display checklist after every operation
        }
    }
}
