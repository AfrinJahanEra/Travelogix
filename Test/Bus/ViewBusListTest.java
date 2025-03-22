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
        Path tempFile = Files.createTempFile("bus_list", ".txt");
        String filePath = tempFile.toAbsolutePath().toString();

        Utilities.FileManager.File.FileHandler fileHandler = new Utilities.FileManager.File.FileHandler(filePath);

        fileHandler.writeToFile("""
                Bus A,Location1,Location2,08:00 AM,ABC-123,1234567890
                Bus B,City1,City2,09:00 AM,XYZ-789,9876543210
                Bus C,Town1,Town2,10:00 AM,PQR-456,4567891230
                """);

      
        Transport.Bus.ViewBusList viewBusList = new Transport.Bus.ViewBusList(filePath);

        
        String result = viewBusList.list(2);

        
        String expectedOutput = """
                No.   Bus Name             Additional Info     Contact Number
                ------------------------------------------------------------
                1     Bus A               Location2           1234567890
                2     Bus B               City2               9876543210
                3     Bus C               Town2               4567891230       
                """;

       
        assertEquals(expectedOutput.strip(), result.strip());

        
        Files.deleteIfExists(tempFile);
    }
}