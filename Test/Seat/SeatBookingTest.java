package Test.Seat;

import org.junit.jupiter.api.Test;

import Transport.Seat.SeatBooking;

import static org.junit.jupiter.api.Assertions.*;

class SeatBookingTest {

    @Test
    void testFindBusDetails_BusFound() {
        SeatBooking seatBooking = new SeatBooking("test_file.txt");
        String[] lines = {
                "Bus1,LocationA,LocationB,10:00 AM,bus-1234,1234567890,4,5",
                "Bus2,LocationC,LocationD,11:00 AM,bus-5678,0987654321,5,6"
        };
        String numberPlate = "bus-1234";

        String[] result = seatBooking.findBusDetails(lines, numberPlate);

        assertNotNull(result);
        assertEquals("Bus1", result[0]);
        assertEquals("LocationA", result[1]);
        assertEquals("LocationB", result[2]);
        assertEquals("10:00 AM", result[3]);
        assertEquals("bus-1234", result[4]);
        assertEquals("1234567890", result[5]);
        assertEquals("4", result[6]);
        assertEquals("5", result[7]);
    }

    @Test
    void testFindBusDetails_BusNotFound() {
        SeatBooking seatBooking = new SeatBooking("test_file.txt");
        String[] lines = {
                "Bus1,LocationA,LocationB,10:00 AM,bus-1234,1234567890,4,5",
                "Bus2,LocationC,LocationD,11:00 AM,bus-5678,0987654321,5,6"
        };
        String numberPlate = "bus-9999";

        String[] result = seatBooking.findBusDetails(lines, numberPlate);

        assertNull(result);
    }
}
