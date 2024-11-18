package Test;

import org.junit.Test;

import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteManager;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteWriter;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

public class NoteWriterTest {

    @Test
    public void testWriteAndSaveNote() throws IOException {
        // Initialize NoteManager and NoteWriter
        NoteManager noteManager = new NoteManager();  // Ensure `NoteManager` manages paths and notes correctly
        NoteWriter noteWriter = new NoteWriter(noteManager);

        // Create the `notes` directory for testing
        new File("notes").mkdir();

        // Set up the test note file path
        String testNoteTitle = "TestNote";
        String testFilePath = noteManager.getNewNoteFilePath(testNoteTitle);
        File testFile = new File(testFilePath);

        // Simulate writing the note
        noteWriter.writeAndSaveNote();

        // Verify that the note file is created
        assertTrue(testFile.exists());

        // Cleanup: Delete the test file
        testFile.delete();
    }
}
