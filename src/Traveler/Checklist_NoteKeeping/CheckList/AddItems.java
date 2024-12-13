package Traveler.Checklist_NoteKeeping.CheckList;

import java.util.Scanner;

public class AddItems {

    public void addItems(Checklist checklist) {
        Scanner scanner = new Scanner(System.in);
        int numItems = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the number of items to add: ");
            try {
                String input = scanner.nextLine();
                numItems = Integer.parseInt(input);
                if (numItems <= 0) {
                    System.out.println("Please enter a positive number.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        for (int i = 1; i <= numItems; i++) {
            System.out.print("Enter item " + i + ": ");
            String item = scanner.nextLine();
            checklist.getItems().add(item);
            checklist.getPackedItems().add(false);
            checklist.increaseTotalItems();
        }

        System.out.println("Items added successfully!\n");
    }
}
