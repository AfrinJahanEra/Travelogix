package Test.Seat;


import org.junit.jupiter.api.Test;

import Transport.Seat.ShowSeat;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowSeatTest {

    @Test
    void testDisplaySeatMatrix() {
        ShowSeat showSeat = new ShowSeat();
        ArrayList<String> bookedSeats = new ArrayList<>();
        bookedSeats.add("1A");
        bookedSeats.add("2B");

        String expectedOutput =
                "[X] [1B] [1C] \n" +
                "[2A] [X] [2C] \n" +
                "[3A] [3B] [3C] \n"; // Assuming 3 columns

        String actualOutput = showSeat.displaySeatMatrix(3, 3, bookedSeats);

        assertEquals(expectedOutput, actualOutput);
    }
}
