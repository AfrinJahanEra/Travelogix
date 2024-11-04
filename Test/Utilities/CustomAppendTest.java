package Test.Utilities;

import Source.Utilities.CustomAppend;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomAppendTest {

    @Test
    void testAppendWithNonNullString() {
        CustomAppend customAppend = new CustomAppend();
        customAppend.append("Hello");
        assertEquals("Hello", customAppend.getResult());
    }

    @Test
    void testAppendWithMultipleStrings() {
        CustomAppend customAppend = new CustomAppend();
        customAppend.append("Hello");
        customAppend.append(", ");
        customAppend.append("World");
        assertEquals("Hello, World", customAppend.getResult());
    }

    @Test
    void testAppendWithNullString() {
        CustomAppend customAppend = new CustomAppend();
        customAppend.append("Hello");
        customAppend.append(null);
        customAppend.append(" World");
        assertEquals("Hello World", customAppend.getResult());
    }

    @Test
    void testAppendWithEmptyString() {
        CustomAppend customAppend = new CustomAppend();
        customAppend.append("");
        assertEquals("", customAppend.getResult());
    }

    @Test
    void testMultipleAppendsWithNulls() {
        CustomAppend customAppend = new CustomAppend();
        customAppend.append(null);
        customAppend.append("Test");
        customAppend.append(null);
        assertEquals("Test", customAppend.getResult());
    }
}
