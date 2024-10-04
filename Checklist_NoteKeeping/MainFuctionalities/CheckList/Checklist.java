package Checklist_NoteKeeping.MainFuctionalities.CheckList;

import java.util.*;

public class Checklist {
    private List<String> items;         // List of items to pack
    private List<Boolean> packedItems;  // Status of packed items (true if packed)
    private int totalItems;             // Total number of items

    public Checklist() {
        items = new ArrayList<>();
        packedItems = new ArrayList<>();
        totalItems = 0;
    }

    // Getter methods for checklist attributes
    public List<String> getItems() {
        return items;
    }

    public List<Boolean> getPackedItems() {
        return packedItems;
    }

    public int getTotalItems() {
        return totalItems;
    }

    // Method to increase the total items count
    public void increaseTotalItems() {
        totalItems++;
    }

    // Method to decrease the total items count
    public void decreaseTotalItems() {
        totalItems--;
    }
}
