package Test.Seat;

import Source.Seat.BusDetailsUpdater;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusDetailsUpdaterTest {

    @Test
    void testUpdateBusDetails() {
        BusDetailsUpdater updater = new BusDetailsUpdater();

        String[] lines = {
                "Bus1,LocationA,LocationB,10:00 AM,bus-1234,1234567890,4,5",
                "Bus2,LocationC,LocationD,11:00 AM,bus-5678,0987654321,5,5"
        };

        String[] originalParts = {"UpdatedBus", "LocationX", "LocationY", "12:00 PM", "bus-1234", "1234567890", "4", "5"};
        String[] bookedSeats = {"1A", "1B"};
        String updatedContent = updater.updateBusDetails(lines, originalParts, bookedSeats);

        String expectedOutput = "UpdatedBus,LocationX,LocationY,12:00 PM,bus-1234,1234567890,4,5,1A,1B\n" +
                "Bus2,LocationC,LocationD,11:00 AM,bus-5678,0987654321,5,5\n";

        assertEquals(expectedOutput.trim(), updatedContent);
    }

    @Test
    void testUpdateBusDetailsNoMatch() {
        BusDetailsUpdater updater = new BusDetailsUpdater();

        String[] lines = {
                "Bus1,LocationA,LocationB,10:00 AM,bus-1234,1234567890,4,5",
                "Bus2,LocationC,LocationD,11:00 AM,bus-5678,0987654321,5,5"
        };

        String[] originalParts = {"UpdatedBus", "LocationX", "LocationY", "12:00 PM", "bus-9999", "1234567890", "4", "5"};
        String[] bookedSeats = {"1A", "1B"};
        String updatedContent = updater.updateBusDetails(lines, originalParts, bookedSeats);

        String expectedOutput = "Bus1,LocationA,LocationB,10:00 AM,bus-1234,1234567890,4,5\n" +
                "Bus2,LocationC,LocationD,11:00 AM,bus-5678,0987654321,5,5\n";

        assertEquals(expectedOutput.trim(), updatedContent);
    }
}
