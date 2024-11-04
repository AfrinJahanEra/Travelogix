package Test;

import org.junit.Before;
import org.junit.Test;

import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteDeleter;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class NoteDeleterTest {
    private NoteManager noteManager;
    private NoteDeleter noteDeleter;

    @Before
    public void setUp() {
        noteManager = new NoteManager();  // Ensure `NoteManager` manages paths and notes correctly
        noteDeleter = new NoteDeleter(noteManager);
        new File("notes").mkdir();  // Create the `notes` directory for testing
    }

    @Test
    public void testDeleteNote() throws IOException {
        String testNoteTitle = "DeleteTestNote";
        String testFilePath = noteManager.getNewNoteFilePath(testNoteTitle);
        File testFile = new File(testFilePath);
        Files.write(testFile.toPath(), Arrays.asList("This note will be deleted."));

        // Ensure the file exists before deletion
        assertTrue(testFile.exists());

        // Delete the note (assume user input is mocked or handled)
        noteDeleter.deleteNote();

        // Verify the file has been deleted
        assertFalse(testFile.exists());
    }
}
