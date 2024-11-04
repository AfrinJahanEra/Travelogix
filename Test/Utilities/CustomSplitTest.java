package Test.Utilities;

import Source.Utilities.CustomSplit;
import Source.Utilities.CustomList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomSplitTest {

    @Test
    void testSplitSimpleInput() {
        CustomSplit customSplit = new CustomSplit();
        customSplit.setInput("apple,banana,orange");
        CustomList<String> parts = customSplit.getParts();
        assertEquals(3, parts.size());
        assertEquals("apple", parts.get(0));
        assertEquals("banana", parts.get(1));
        assertEquals("orange", parts.get(2));
    }

    @Test
    void testSplitWithLeadingAndTrailingCommas() {
        CustomSplit customSplit = new CustomSplit();
        customSplit.setInput(",apple,banana,orange,");
        CustomList<String> parts = customSplit.getParts();
        assertEquals(3, parts.size());
        assertEquals("apple", parts.get(0));
        assertEquals("banana", parts.get(1));
        assertEquals("orange", parts.get(2));
    }

    @Test
    void testSplitWithEmptyInput() {
        CustomSplit customSplit = new CustomSplit();
        customSplit.setInput("");
        CustomList<String> parts = customSplit.getParts();
        assertEquals(0, parts.size());
    }

    @Test
    void testSplitSingleItem() {
        CustomSplit customSplit = new CustomSplit();
        customSplit.setInput("apple");
        CustomList<String> parts = customSplit.getParts();
        assertEquals(1, parts.size());
        assertEquals("apple", parts.get(0));
    }

    @Test
    void testSplitWithConsecutiveCommas() {
        CustomSplit customSplit = new CustomSplit();
        customSplit.setInput("apple,,banana,,orange");
        CustomList<String> parts = customSplit.getParts();
        assertEquals(4, parts.size());
        assertEquals("apple", parts.get(0));
        assertEquals("", parts.get(1)); // empty string between commas
        assertEquals("banana", parts.get(2));
        assertEquals("", parts.get(3)); // empty string between commas
    }

    @Test
    void testSplitWithNoCommas() {
        CustomSplit customSplit = new CustomSplit();
        customSplit.setInput("apple");
        CustomList<String> parts = customSplit.getParts();
        assertEquals(1, parts.size());
        assertEquals("apple", parts.get(0));
    }
}
