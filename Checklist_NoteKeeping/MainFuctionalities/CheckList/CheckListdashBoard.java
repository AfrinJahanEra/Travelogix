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


            
            displayCheckList.displayChecklist(checklist);  
        }
    }
}
