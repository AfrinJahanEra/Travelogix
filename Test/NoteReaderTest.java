package Test;

import org.junit.Before;
import org.junit.Test;

import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteManager;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class NoteReaderTest {
    private NoteManager noteManager;
    private NoteReader noteReader;

    @Before
    public void setUp() {
        noteManager = new NoteManager();  // Ensure `NoteManager` manages paths and notes correctly
        noteReader = new NoteReader(noteManager);
        new File("notes").mkdir();  // Create the `notes` directory for testing
    }

    @Test
    public void testShowAndReadNote() throws IOException {
        String testNoteTitle = "ReadTestNote";
        String testFilePath = noteManager.getNewNoteFilePath(testNoteTitle);
        File testFile = new File(testFilePath);
        Files.write(testFile.toPath(), Arrays.asList("This is a test note."));

        // Invoke the reader to read the file (assume user input is mocked or handled)
        noteReader.showAndReadNote();

        // Cleanup
        testFile.delete();
    }
}
