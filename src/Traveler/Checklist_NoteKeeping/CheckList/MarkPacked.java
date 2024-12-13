package Traveler.Checklist_NoteKeeping.CheckList;

import java.util.Scanner;

public class MarkPacked {

    public void markPacked(Checklist checklist) {
        DisplayCheckList display = new DisplayCheckList();
        display.displayChecklist(checklist);

        if (checklist.getItems().isEmpty()) {
            System.out.println("No items available to mark as packed!\n");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int itemNumber = -1;

        while (!validInput) {
            System.out.print("Enter the item number to mark as packed: ");
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

        if (checklist.getPackedItems().get(itemNumber - 1)) {
            System.out.println("Item is already packed!\n");
        } else {
            checklist.getPackedItems().set(itemNumber - 1, true);
            checklist.decreaseTotalItems();
            System.out.println("Item marked as packed!\n");
        }
    }
}
