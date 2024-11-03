package Traveler.Checklist_NoteKeeping.CheckList;

import java.util.Scanner;

public class DeleteItem //extends Checklist
{

    // @Override
    // public void manageChecklist() {
    //     deleteItem();
    // }


    public void deleteChecklistItem(Checklist checklist) {
        Scanner scanner = new Scanner(System.in);

        if (checklist.getItems().isEmpty()) {
            System.out.println("No items to delete!\n");
            return;
        }

        DisplayCheckList display = new DisplayCheckList();
        display.displayChecklist(checklist);  // Display the current checklist before deletion

        System.out.print("Enter the item number to delete: ");
        int itemNumber = scanner.nextInt();

        if (itemNumber < 1 || itemNumber > checklist.getItems().size()) {
            System.out.println("Invalid item number!\n");
        } else {
            // Remove the selected item and its packed status
            checklist.getItems().remove(itemNumber - 1);
            checklist.getPackedItems().remove(itemNumber - 1);
            checklist.decreaseTotalItems();
            System.out.println("Item deleted successfully!\n");
        }
    }
}
