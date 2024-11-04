package Test.Utilities;

import Source.Utilities.CustomParseInt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomParseIntTest {

    @Test
    void testParsePositiveInteger() {
        CustomParseInt parser = new CustomParseInt("12345");
        assertEquals(12345, parser.parseInt());
    }

    @Test
    void testParseNegativeInteger() {
        CustomParseInt parser = new CustomParseInt("-6789");
        assertEquals(-6789, parser.parseInt());
    }

    @Test
    void testParseZero() {
        CustomParseInt parser = new CustomParseInt("0");
        assertEquals(0, parser.parseInt());
    }

    @Test
    void testParseInvalidCharacters() {
        CustomParseInt parser = new CustomParseInt("123a45");
        assertThrows(NumberFormatException.class, parser::parseInt);
    }

    @Test
    void testParseEmptyString() {
        CustomParseInt parser = new CustomParseInt("");
        assertThrows(NumberFormatException.class, parser::parseInt);
    }

    @Test
    void testParseOnlyNegativeSign() {
        CustomParseInt parser = new CustomParseInt("-");
        assertThrows(NumberFormatException.class, parser::parseInt);
    }
}

