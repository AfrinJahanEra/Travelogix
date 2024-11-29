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
        bookedSeats.add("4D");

        String expectedOutput =
                "[ X ] [ 1B] [ 1C] [ 1D] \n" +
                "[ 2A] [ X ] [ 2C] [ 2D] \n" +
                "[ 3A] [ 3B] [ 3C] [ 3D] \n" +
                "[ 4A] [ 4B] [ 4C] [ X ] \n";

        String actualOutput = showSeat.displaySeatMatrix(4, 4, bookedSeats);

        assertEquals(expectedOutput, actualOutput);
    }
}
