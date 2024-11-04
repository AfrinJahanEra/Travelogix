package Test.Utilities;

import Source.Utilities.CustomList;
import Source.Utilities.CustomUpperCase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomUpperCaseTest {

    @Test
    void testToUpperCaseWithLowercase() {
        CustomList<Character> input = new CustomList<>();
        input.add('h');
        input.add('e');
        input.add('l');
        input.add('l');
        input.add('o');
        input.add(' ');

        CustomUpperCase customUpperCase = new CustomUpperCase(input);
        assertEquals("HELLO ", customUpperCase.toUpperCase());
    }

    @Test
    void testToUpperCaseWithEmptyInput() {
        CustomList<Character> input = new CustomList<>();

        CustomUpperCase customUpperCase = new CustomUpperCase(input);
        assertEquals("", customUpperCase.toUpperCase());
    }

    @Test
    void testToUpperCaseWithSpecialCharacters() {
        CustomList<Character> input = new CustomList<>();
        input.add('!');
        input.add('@');
        input.add('#');

        CustomUpperCase customUpperCase = new CustomUpperCase(input);
        assertEquals("!@#", customUpperCase.toUpperCase());
    }
}
