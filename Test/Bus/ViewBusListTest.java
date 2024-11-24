package Test.Bus;


import org.junit.jupiter.api.Test;

import Transport.Bus.ViewBusList;
import Utilities.FileManager.File.FileHandler;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;



class ViewBusListTest {
    @Test
    void testList() throws IOException {
        // Create a temporary file to simulate the file system
        Path tempFile = Files.createTempFile("bus_list", ".txt");
        String filePath = tempFile.toAbsolutePath().toString();

        // Create a FileHandler instance to write test data
        Utilities.FileManager.File.FileHandler fileHandler = new Utilities.FileManager.File.FileHandler(filePath);

        // Write test data to the file
        fileHandler.writeToFile("""
                Bus A,Location1,Location2,08:00 AM,ABC-123,1234567890
                Bus B,City1,City2,09:00 AM,XYZ-789,9876543210
                Bus C,Town1,Town2,10:00 AM,PQR-456,4567891230
                """);

        // Create an instance of ViewBusList
        Transport.Bus.ViewBusList viewBusList = new Transport.Bus.ViewBusList(filePath);

        // Call the method with n = 2 (Ending Location)
        String result = viewBusList.list(2);

        // Expected Output
        String expectedOutput = """
                No.   Bus Name             Additional Info     Contact Number
                ------------------------------------------------------------
                1     Bus A               Location2           1234567890
                2     Bus B               City2               9876543210
                3     Bus C               Town2               4567891230       
                """;

        // Assert the output matches the expected format
        assertEquals(expectedOutput.strip(), result.strip());

        // Cleanup: Delete the temporary file
        Files.deleteIfExists(tempFile);
    }
}