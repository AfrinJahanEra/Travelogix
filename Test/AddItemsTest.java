package Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import Traveler.Checklist_NoteKeeping.CheckList.*;

public class AddItemsTest {
    private Checklist checklist;
    private AddItems addItems;

    @Before
    public void setUp() {
        checklist = new Checklist();
        addItems = new AddItems();
    }

    @Test
    public void testAddItems() {
        // Simulate adding items to the checklist
        checklist.getItems().add("Item 1");
        checklist.increaseTotalItems();

        // Verify the item was added
        assertEquals(1, checklist.getTotalItems());
        assertTrue(checklist.getItems().contains("Item 1"));
    }
}
