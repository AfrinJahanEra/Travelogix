package Test.Utilities;

import Source.Utilities.CustomEquals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CustomEqualsTest {

    @Test
    void testEqualsWithEqualStrings() {
        CustomEquals<String> customEquals = new CustomEquals<>("hello", "hello");
        assertTrue(customEquals.equals());
    }

    @Test
    void testEqualsWithDifferentStrings() {
        CustomEquals<String> customEquals = new CustomEquals<>("hello", "world");
        assertFalse(customEquals.equals());
    }

    @Test
    void testEqualsWithNulls() {
        CustomEquals<String> customEquals1 = new CustomEquals<>(null, null);
        assertTrue(customEquals1.equals());

        CustomEquals<String> customEquals2 = new CustomEquals<>(null, "test");
        assertFalse(customEquals2.equals());

        CustomEquals<String> customEquals3 = new CustomEquals<>("test", null);
        assertFalse(customEquals3.equals());
    }

    @Test
    void testEqualsWithDifferentTypes() {
        CustomEquals<Object> customEquals = new CustomEquals<>(new Object(), new Object());
        assertFalse(customEquals.equals());
    }
}
