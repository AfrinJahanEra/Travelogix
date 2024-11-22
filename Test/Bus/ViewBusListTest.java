package Test.Bus;


import org.junit.jupiter.api.Test;

import Transport.Bus.ViewBusList;
import Utilities.FileManager.File.FileHandler;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;



class ViewBusListTest {
    @Test
    void testList() throws IOException {
        String testFilePath = "test_bus.txt";
        FileHandler fileHandler = new FileHandler(testFilePath);

        // Write test data to the file
        PrintStream writer = new PrintStream(testFilePath);
        writer.println("1st Old Bus Name,1st Old Start City,1st Old End City,1st Old Time,1st Old Plate,1st Old Phone,5,4");
        writer.println("2nd Old Bus Name,2nd Old Start City,2nd Old End City,2nd Old Time,2nd Old Plate,2nd Old Phone,5,4");
        writer.println("3rd Old Bus Name,3rd Old Start City,3rd Old End City,3rd Old Time,3rd Old Plate,3rd Old Phone,5,4");
        writer.close();

        // Initialize ViewBusList and call list method
        ViewBusList viewBusList = new ViewBusList(testFilePath);
        String expectedOutput = "1. 1st Old Bus Name-1st Old Start City contact no. 1st Old Phone\n"
                + "2. 2nd Old Bus Name-2nd Old Start City contact no. 2nd Old Phone\n"
                + "3. 3rd Old Bus Name-3rd Old Start City contact no. 3rd Old Phone\n";

        String actualOutput = viewBusList.list(1);

        assertEquals(expectedOutput, actualOutput);

        new File(testFilePath).delete();
    }
}