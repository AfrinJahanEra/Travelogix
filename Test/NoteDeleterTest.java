package Test;

import org.junit.Test;

import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteDeleter;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class NoteDeleterTest {

    @Test
    public void testDeleteNote() throws IOException {
        // Initialize NoteManager and NoteDeleter
        NoteManager noteManager = new NoteManager();  // Ensure `NoteManager` manages paths and notes correctly
        NoteDeleter noteDeleter = new NoteDeleter(noteManager);

        // Create the `notes` directory for testing
        new File("notes").mkdir();

        // Set up a test note file
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
