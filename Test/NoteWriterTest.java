package Test;

import org.junit.Before;
import org.junit.Test;

import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteManager;
import Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote.NoteWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class NoteWriterTest {
    private NoteManager noteManager;
    private NoteWriter noteWriter;

    @Before
    public void setUp() {
        noteManager = new NoteManager();  // Ensure `NoteManager` manages paths and notes correctly
        noteWriter = new NoteWriter(noteManager);
        new File("notes").mkdir();  // Create the `notes` directory for testing
    }

    @Test
    public void testWriteAndSaveNote() throws IOException {
        String testNoteTitle = "TestNote";
        String testFilePath = noteManager.getNewNoteFilePath(testNoteTitle);
        File testFile = new File(testFilePath);

        // Simulate writing the note
        noteWriter.writeAndSaveNote();

        // Verify that the note file is created
        assertTrue(testFile.exists());

        // Cleanup
        testFile.delete();
    }
}
