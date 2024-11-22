package Test.Bus;

import Source.Bus.ViewBusDetails;
import Source.File.FileHandler;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewBusDetailsTest {
    @Test
    void testViewBusDetails() throws IOException {
        String testFilePath = "test_bus.txt";
        FileHandler fileHandler = new FileHandler(testFilePath);
        try (PrintWriter writer = new PrintWriter(new File(testFilePath))) {
            writer.println("1st Old Bus Name,1st Old Start City,1st Old End City,1st Old Time,1st Old Plate,1st Old Phone,5,4");
            writer.println("2nd Old Bus Name,2nd Old Start City,2nd Old End City,2nd Old Time,2nd Old Plate,2nd Old Phone,5,4");
        }

        ViewBusDetails viewBusDetails = new ViewBusDetails(testFilePath);

        String expectedOutput1 = "Current details:\n" +
                "Bus name: 1st Old Bus Name\n" +
                "Bus starting location: 1st Old Start City\n" +
                "Bus ending location: 1st Old End City\n" +
                "Bus starting time: 1st Old Time\n" +
                "Bus number plate: 1st Old Plate\n" +
                "Bus contact number: 1st Old Phone\n" +
                "No seats booked yet from this bus\n";
        String actualOutput1 = viewBusDetails.viewBusDetails("1st Old Plate");
        assertEquals(expectedOutput1, actualOutput1);

        String expectedOutput2 = "Bus not found!!\n";
        String actualOutput2 = viewBusDetails.viewBusDetails("Non-existing Plate");
        assertEquals(expectedOutput2, actualOutput2);
    }
}
