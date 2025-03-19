package Traveler.Checklist_NoteKeeping.CheckList;

public class DisplayCheckList 
{

   
    public void displayChecklist(Checklist checklist) {
        System.out.println("\n--- Checklist ---");
        for (int i = 0; i < checklist.getItems().size(); i++) {
            String status = checklist.getPackedItems().get(i) ? "(X)" : "(.)";
            System.out.println((i + 1) + ". " + status + " " + checklist.getItems().get(i));
        }
        System.out.println("Total items: " + checklist.getTotalItems() + "\n");
    }
    
}
