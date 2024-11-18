package Test.Bus;


import org.junit.jupiter.api.Test;

import Transport.Bus.AddBus;
import Utilities.FileManager.File.FileHandler;

import java.io.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddBusTest {
    
    private final String testFilePath = "test_bus.txt";


    @Test
    void testAddBusWithValidInputs() throws IOException {

        String input = "Test Bus\nStart City\nEnd City\n08:00 AM\nAB123\n1234567890\n5\n4\n";

        AddBus addBus = new AddBus(testFilePath);
        addBus.savedetails(input);

        FileHandler fileHandler = new FileHandler(testFilePath);
        String fileContent = fileHandler.readFromFile();

        assertTrue(fileContent.contains("Test Bus,Start City,End City,08:00 AM,AB123,1234567890,5,4"));
    }
}
