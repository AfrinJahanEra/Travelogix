package Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Traveler.Checklist_NoteKeeping.CheckList.*;

public class DeleteItemTest {
    private Checklist checklist;
    private DeleteItem deleteItem;

    @Before
    public void setUp() {
        checklist = new Checklist();
        deleteItem = new DeleteItem();
        checklist.getItems().add("Item 1");
        checklist.getItems().add("Item 2");
        checklist.getPackedItems().add(false);
        checklist.getPackedItems().add(false);
        checklist.increaseTotalItems();
        checklist.increaseTotalItems();
    }

    @Test
    public void testDeleteItem() {
        // Initial assertions
        assertEquals(2, checklist.getTotalItems());

        // Simulate deleting an item
        deleteItem.deleteChecklistItem(checklist);

        // Verify item deletion
        assertEquals(1, checklist.getTotalItems());
        assertFalse(checklist.getItems().contains("Item 1"));  // Assuming the first item is deleted
    }
}
