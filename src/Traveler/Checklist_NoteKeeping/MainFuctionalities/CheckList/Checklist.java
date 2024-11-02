package Checklist_NoteKeeping.MainFuctionalities.CheckList;

import java.util.*;

public class Checklist {
    private List<String> items;         
    private List<Boolean> packedItems;  
    private int totalItems;    
    


    public Checklist() {
        items = new ArrayList<>();
        packedItems = new ArrayList<>();
        totalItems = 0;
    }

    public List<String> getItems() {
        return items;
    }

    public List<Boolean> getPackedItems() {
        return packedItems;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void increaseTotalItems() {
        totalItems++;
    }

    public void decreaseTotalItems() {
        totalItems--;
    }

    // public abstract void manageChecklist();
    
}
