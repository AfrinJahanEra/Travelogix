package Traveler.Checklist_NoteKeeping.CheckList;

import java.util.Scanner;

public class MarkPacked //extends Checklist
{

    // @Override
    // public void manageChecklist() {
    //     markPacked();
    // }

    public void markPacked(Checklist checklist) {
        DisplayCheckList display = new DisplayCheckList();
        display.displayChecklist(checklist);  
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the item number to mark as packed: ");
        int itemNumber = scanner.nextInt();
        
        if (itemNumber < 1 || itemNumber > checklist.getItems().size()) {
            System.out.println("Invalid item number!\n");
        } else if (checklist.getPackedItems().get(itemNumber - 1)) {
            System.out.println("Item is already packed!\n");
        } else {
            checklist.getPackedItems().set(itemNumber - 1, true);
            checklist.decreaseTotalItems();  
            System.out.println("Item marked as packed!\n");
        }
    }
}
