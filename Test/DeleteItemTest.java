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

        // Verify pre-deletion state
        assertEquals("Initial total items should be 2", 2, checklist.getTotalItems());
        assertTrue("Checklist should contain 'Item 1'", checklist.getItems().contains("Item 1"));

        // Perform item deletion
        deleteItem.deleteChecklistItem(checklist);

        // Verify post-deletion state
        assertEquals("Total items should decrease to 1", 1, checklist.getTotalItems());
        assertFalse("Checklist should no longer contain 'Item 1'", checklist.getItems().contains("Item 1"));
    }
}
