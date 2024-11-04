package Test;


import Traveler.Checklist_NoteKeeping.CheckList.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MarkPackedTest {
    private Checklist checklist;
    private MarkPacked markPacked;

    @Before
    public void setUp() {
        checklist = new Checklist();
        markPacked = new MarkPacked();
        checklist.getItems().add("Item 1");
        checklist.getPackedItems().add(false);
        checklist.increaseTotalItems();
    }

    @Test
    public void testMarkPacked() {
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
        checklist.getPackedItems().set(0, true);  // Manually mark item as packed
        markPacked.markPacked(checklist);

        // Verify that the item is still packed and no changes were incorrectly made
        assertTrue(checklist.getPackedItems().get(0));
    }
}
