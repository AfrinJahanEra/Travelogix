package Test.Seat;

import org.junit.jupiter.api.Test;

import Transport.Seat.SeatManager;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SeatManagerTest {

    @Test
    void testSetupSeatManager() {
        String[] busDetails = {"Bus1", "LocationA", "LocationB", "08:00", "XYZ123", "1234567890", "3", "4"};
        SeatManager seatManager = new SeatManager();
        seatManager.setupSeatManager(busDetails);

        assertEquals(3, seatManager.getRow());
        assertEquals(4, seatManager.getCol());
    }

    @Test
    void testBookSeats() throws IOException {
        String[] busDetails = {"Bus1", "LocationA", "LocationB", "08:00", "XYZ123", "1234567890", "3", "4"};
        SeatManager seatManager = new SeatManager();
        seatManager.setupSeatManager(busDetails);


        ArrayList<String> bookedSeats = new ArrayList<>();
        bookedSeats.add("1A");
        seatManager.bookedSeats = bookedSeats;

        assertTrue(seatManager.bookedSeats.contains("1A"));
        assertFalse(seatManager.bookedSeats.contains("1B"));
    }

    @Test
    void testUpdateBusDetails() {
        String[] lines = {
                "Bus1,LocationA,LocationB,08:00,XYZ123,1234567890,3,4",
                "Bus2,LocationC,LocationD,09:00,ABC456,0987654321,3,4"
        };
        String[] originalParts = {"Bus1", "LocationA", "LocationB", "08:00", "XYZ123", "1234567890", "3", "4"};
        SeatManager seatManager = new SeatManager();
        seatManager.setupSeatManager(originalParts);
        seatManager.bookedSeats.add("1A");

        String updatedLines = seatManager.updateBusDetails(lines, originalParts);

        // Check the updated lines
        assertTrue(updatedLines.contains("1A"));
        assertTrue(updatedLines.contains("Bus2"));
    }
}
