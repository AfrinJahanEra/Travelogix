package Test.File;

import Source.File.FileHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileHandlerTest {
    private final String testFilePath = "test_File.txt";
    private FileHandler fileHandler = new FileHandler(testFilePath);

    @Test
    void testWriteToFile() throws IOException {
        String data = "Hello, World!";
        fileHandler.writeToFile(data);

        String content = fileHandler.readFromFile();
        assertEquals("Hello, World!\n", content);
        new java.io.File(testFilePath).delete();
    }

    @Test
    void testReadFromFile() throws IOException {
        String data = "Test content.";
        fileHandler.writeToFile(data);

        String content = fileHandler.readFromFile();
        assertEquals("Test content.\n", content);
        new java.io.File(testFilePath).delete();
    }

    @Test
    void testAppendToFile() throws IOException {
        String initialData = "First line.";
        String appendData = "Second line.";


        fileHandler.writeToFile(initialData);
        fileHandler.appendToFile(appendData);

        String content = fileHandler.readFromFile();
        assertEquals("First line.\nSecond line.\n", content);
        new java.io.File(testFilePath).delete();
    }
}
