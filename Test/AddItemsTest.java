package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Traveler.Checklist_NoteKeeping.CheckList.*;

public class AddItemsTest {
    Checklist checklist=new Checklist();
    AddItems addItems=new AddItems();

    @Test
    public void testAddItems() {
    
        checklist.getItems().add("Item 1");
        checklist.increaseTotalItems();

        assertEquals(1, checklist.getTotalItems());
        assertTrue(checklist.getItems().contains("Item 1"));
    }
}
