package Test.Utilities;

import Source.Utilities.CustomTrim;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomTrimTest {

    @Test
    void testTrimSimpleSpaces() {
        CustomTrim customTrim = new CustomTrim("   Hello World!   ");
        assertEquals("Hello World!", customTrim.trim());
    }

    @Test
    void testTrimLeadingSpaces() {
        CustomTrim customTrim = new CustomTrim("   Hello");
        assertEquals("Hello", customTrim.trim());
    }

    @Test
    void testTrimTrailingSpaces() {
        CustomTrim customTrim = new CustomTrim("World!   ");
        assertEquals("World!", customTrim.trim());
    }

    @Test
    void testTrimNoSpaces() {
        CustomTrim customTrim = new CustomTrim("NoSpacesHere");
        assertEquals("NoSpacesHere", customTrim.trim());
    }

    @Test
    void testTrimAllSpaces() {
        CustomTrim customTrim = new CustomTrim("       ");
        assertEquals("", customTrim.trim());
    }

    @Test
    void testTrimEmptyString() {
        CustomTrim customTrim = new CustomTrim("");
        assertEquals("", customTrim.trim());
    }
}
