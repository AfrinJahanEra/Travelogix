// package Traveler.Checklist_NoteKeeping.CheckList;

// import java.util.Scanner;

// public class DeleteItem //extends Checklist
// {

//     // @Override
//     // public void manageChecklist() {
//     //     deleteItem();
//     // }


//     public void deleteChecklistItem(Checklist checklist) {
//         Scanner scanner = new Scanner(System.in);

//         if (checklist.getItems().isEmpty()) {
//             System.out.println("No items to delete!\n");
//             return;
//         }

//         DisplayCheckList display = new DisplayCheckList();
//         display.displayChecklist(checklist);  // Display the current checklist before deletion

//         System.out.print("Enter the item number to delete: ");
//         int itemNumber = scanner.nextInt();

//         if (itemNumber < 1 || itemNumber > checklist.getItems().size()) {
//             System.out.println("Invalid item number!\n");
//         } else {
//             // Remove the selected item and its packed status
//             checklist.getItems().remove(itemNumber - 1);
//             checklist.getPackedItems().remove(itemNumber - 1);
//             checklist.decreaseTotalItems();
//             System.out.println("Item deleted successfully!\n");
//         }
//     }
// }
package Traveler.Checklist_NoteKeeping.CheckList;

import java.util.Scanner;

public class DeleteItem {

    public void deleteChecklistItem(Checklist checklist) {
        Scanner scanner = new Scanner(System.in);

        if (checklist.getItems().isEmpty()) {
            System.out.println("No items to delete!\n");
            return;
        }

        DisplayCheckList display = new DisplayCheckList();
        display.displayChecklist(checklist); // Display the current checklist before deletion

        boolean validInput = false;
        int itemNumber = -1;

        while (!validInput) {
            System.out.print("Enter the item number to delete: ");
            try {
                String input = scanner.nextLine();
                itemNumber = Integer.parseInt(input);

                if (itemNumber >= 1 && itemNumber <= checklist.getItems().size()) {
                    validInput = true;
                } else {
                    System.out.println("Invalid item number. Please enter a number between 1 and " + checklist.getItems().size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Remove the selected item and its packed status
        checklist.getItems().remove(itemNumber - 1);
        checklist.getPackedItems().remove(itemNumber - 1);
        checklist.decreaseTotalItems();
        System.out.println("Item deleted successfully!\n");
    }
}
