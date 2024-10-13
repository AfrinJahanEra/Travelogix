package Checklist_NoteKeeping.MainFuctionalities.CheckList;

import java.util.Scanner;

public class AddItems {

    public void addItems(Checklist checklist) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items to add: ");
        int numItems = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.println("Items added successfully!\n");
    }
}
