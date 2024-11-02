package Checklist_NoteKeeping.MainFuctionalities.CheckList;

import java.util.Scanner;

public class AddItems // extends Checklist 
{

    // @Override
    // public void manageChecklist() {
    //     addItems();
    // }

    public void addItems(Checklist checklist) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items to add: ");
        int numItems = scanner.nextInt();
        scanner.nextLine(); 


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
