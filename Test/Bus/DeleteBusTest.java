package Test.Bus;


import org.junit.jupiter.api.Test;

import Transport.Bus.DeleteBus;
import Utilities.FileManager.File.FileHandler;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteBusTest {

    @Test
    void testDeleteBusWithExistingNumberPlate() throws IOException {
        String  scanner = "AB123\n";
        String testFilePath = "test_bus.txt";
        FileHandler fileHandler = new FileHandler(testFilePath);
        fileHandler.writeToFile("Test Bus,Start City,End City,08:00 AM,AB123,1234567890,5,4");
        DeleteBus deleteBus = new DeleteBus(testFilePath);
        deleteBus.deleteBus(scanner);
        String fileContent = fileHandler.readFromFile();
        assertFalse(fileContent.contains("AB123"));
        new java.io.File(testFilePath).delete();
    }

    @Test
    void testDeleteBusWithNonExistingNumberPlate() throws IOException {
        String scanner = "XYZ999\n";
        String testFilePath = "test_bus.txt";
        FileHandler fileHandler = new FileHandler(testFilePath);
        fileHandler.writeToFile("Test Bus,Start City,End City,08:00 AM,AB123,1234567890,5,4");
        DeleteBus deleteBus = new DeleteBus(testFilePath);
        deleteBus.deleteBus(scanner);
        String fileContent = fileHandler.readFromFile();
        assertTrue(fileContent.contains("AB123"));
        new java.io.File(testFilePath).delete();
    }
}