package Test.Bus;

import org.junit.jupiter.api.Test;

import Transport.Bus.DeleteBus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeleteBusTest {

    @Test
    void testDeleteBus_BusFound() throws IOException {
      
        File tempFile = createTempFileWithContent("Bus1,ModelX,50,AC,1234\nBus2,ModelY,45,NonAC,5678\n");
        DeleteBus deleteBus = new DeleteBus(tempFile.getAbsolutePath());

      
        deleteBus.deleteBus("5678");

        String updatedContent = Files.readString(tempFile.toPath());
        assertTrue(updatedContent.contains("Bus1,ModelX,50,AC,1234"));
        assertFalse(updatedContent.contains("Bus2,ModelY,45,NonAC,5678"));

        tempFile.delete();
    }

    @Test
    void testDeleteBus_BusNotFound() throws IOException {
        
        File tempFile = createTempFileWithContent("Bus1,ModelX,50,AC,1234\nBus2,ModelY,45,NonAC,5678\n");
        DeleteBus deleteBus = new DeleteBus(tempFile.getAbsolutePath());

        deleteBus.deleteBus("9999");

        String updatedContent = Files.readString(tempFile.toPath());
        assertTrue(updatedContent.contains("Bus1,ModelX,50,AC,1234"));
        assertTrue(updatedContent.contains("Bus2,ModelY,45,NonAC,5678"));

        tempFile.delete();
    }

    private File createTempFileWithContent(String content) throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        Files.writeString(tempFile.toPath(), content);
        return tempFile;
    }
}
