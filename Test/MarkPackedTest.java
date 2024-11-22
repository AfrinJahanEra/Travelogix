package Test;

import Traveler.Checklist_NoteKeeping.CheckList.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class MarkPackedTest {

    @Test
    public void testMarkPacked() {
        // Initialize checklist and markPacked
        Checklist checklist = new Checklist();
        MarkPacked markPacked = new MarkPacked();

        // Add initial items
        checklist.getItems().add("Item 1");
        checklist.getPackedItems().add(false);
        checklist.increaseTotalItems();

        // Verify initial state
        assertFalse(checklist.getPackedItems().get(0));

        // Mark the item as packed
        markPacked.markPacked(checklist);

        // Verify that the item is marked as packed
        assertTrue(checklist.getPackedItems().get(0));
        assertEquals(1, checklist.getTotalItems());  // Ensure total items are still logical
    }

    @Test
    public void testMarkPackedAlreadyPacked() {
        // Initialize checklist and markPacked
        Checklist checklist = new Checklist();
        MarkPacked markPacked = new MarkPacked();

        // Add initial items
        checklist.getItems().add("Item 1");
        checklist.getPackedItems().add(true);  // Item already packed
        checklist.increaseTotalItems();

        // Mark the item as packed
        markPacked.markPacked(checklist);

        // Verify that the item is still packed and no changes were incorrectly made
        assertTrue(checklist.getPackedItems().get(0));
    }
}
