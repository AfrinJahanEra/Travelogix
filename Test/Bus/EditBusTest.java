package Test.Bus;

import Source.Bus.EditBus;
import Source.File.FileHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EditBusTest {

    @Test
    void testUpdateDetailWithInput() throws IOException {
        String testFilePath = "test_bus.txt";
        FileHandler fileHandler = new FileHandler(testFilePath);
        fileHandler.writeToFile("Old Bus Name,Old Start City,Old End City,Old Time,Old Plate,Old Phone,5,4");

        EditBus editBus = new EditBus(testFilePath);
        String[] parts = {"Old Bus Name", "Old Start City", "Old End City", "Old Time", "Old Plate", "Old Phone", "5", "4"};

        Scanner scanner = new Scanner("New Bus Name\n");
        editBus.updateDetail("Enter new bus name", parts, 0, scanner);
        assertEquals("New Bus Name", parts[0]);

        Scanner scanner1 = new Scanner("\n");
        editBus.updateDetail("Enter new bus name", parts, 0, scanner1);
        assertEquals("New Bus Name", parts[0]);

        new java.io.File(testFilePath).delete();
    }
}
