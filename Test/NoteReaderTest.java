package Test;

import org.junit.Test;

import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteManager;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class NoteReaderTest {

    @Test
    public void testShowAndReadNote() throws IOException {
        // Initialize NoteManager and NoteReader
        NoteManager noteManager = new NoteManager();  // Ensure `NoteManager` manages paths and notes correctly
        NoteReader noteReader = new NoteReader(noteManager);

        // Create the `notes` directory for testing
        new File("notes").mkdir();

        // Set up a test note file
        String testNoteTitle = "ReadTestNote";
        String testFilePath = noteManager.getNewNoteFilePath(testNoteTitle);
        File testFile = new File(testFilePath);
        Files.write(testFile.toPath(), Arrays.asList("This is a test note."));

        // Invoke the reader to read the file (assume user input is mocked or handled)
        noteReader.showAndReadNote();

        // Cleanup: Delete the test file
        testFile.delete();
    }
}
