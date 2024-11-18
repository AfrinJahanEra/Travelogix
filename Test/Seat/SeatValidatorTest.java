package Test.Seat;

import org.junit.jupiter.api.Test;

import Transport.Seat.SeatValidator;

import static org.junit.jupiter.api.Assertions.*;

class SeatValidatorTest {

    @Test
    void testIsSeatValid() {
        SeatValidator validator = new SeatValidator(3, 3);
        // Valid seats
        assertTrue(validator.isSeatValid("1A"));
        assertTrue(validator.isSeatValid("2B"));
        assertTrue(validator.isSeatValid("3C"));

        // Invalid seats
        assertFalse(validator.isSeatValid("0A"));
        assertFalse(validator.isSeatValid("4A"));
        assertFalse(validator.isSeatValid("1D"));
        assertFalse(validator.isSeatValid("2"));
        assertFalse(validator.isSeatValid(""));
        assertFalse(validator.isSeatValid("3"));
        assertFalse(validator.isSeatValid("3Z"));
    }
}
