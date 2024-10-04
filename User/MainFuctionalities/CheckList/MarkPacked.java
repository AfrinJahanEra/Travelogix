package User.MainFuctionalities.CheckList;

import java.util.Scanner;

public class MarkPacked {

    // Method to mark items as packed
    public void markPacked(Checklist checklist) {
        DisplayCheckList display = new DisplayCheckList();
        display.displayChecklist(checklist);  // Show current checklist
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the item number to mark as packed: ");
        int itemNumber = scanner.nextInt();
        
        if (itemNumber < 1 || itemNumber > checklist.getItems().size()) {
            System.out.println("Invalid item number!\n");
        } else if (checklist.getPackedItems().get(itemNumber - 1)) {
            System.out.println("Item is already packed!\n");
        } else {
            checklist.getPackedItems().set(itemNumber - 1, true);
            checklist.decreaseTotalItems();  // Decrease total items to pack
            System.out.println("Item marked as packed!\n");
        }
    }
}
