package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import Traveler.Checklist_NoteKeeping.CheckList.*;

public class DeleteItemTest {
    @Test
    public void testDeleteItem() {
        // Initialize checklist and deleteItem
        Checklist checklist = new Checklist();
        DeleteItem deleteItem = new DeleteItem();
        
        // Add initial items
        checklist.getItems().add("Item 1");
        checklist.getItems().add("Item 2");
        checklist.getPackedItems().add(false);
        checklist.getPackedItems().add(false);
        checklist.increaseTotalItems();
        checklist.increaseTotalItems();

        // Initial assertions
        assertEquals(2, checklist.getTotalItems());

        // Simulate deleting an item
        deleteItem.deleteChecklistItem(checklist);

        // Verify item deletion
        assertEquals(1, checklist.getTotalItems());
        assertFalse(checklist.getItems().contains("Item 1"));
    }
}
