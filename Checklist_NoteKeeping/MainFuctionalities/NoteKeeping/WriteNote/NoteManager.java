package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.WriteNote;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    private static final String DIRECTORY_PATH = "notes/";
    private static final String METADATA_FILE = "notes_metadata.txt";

    public NoteManager() {
        File dir = new File(DIRECTORY_PATH);
        if (!dir.exists()) {
            dir.mkdir();  // Create directory if it doesn't exist
        }
    }

    // Get a list of all the saved notes with metadata
    public List<String> getNotesWithMetadata() {
        List<String> notesWithMetadata = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(METADATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notesWithMetadata.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the metadata file.");
            e.printStackTrace();
        }
        return notesWithMetadata;
    }

    // Get file path for a new note
    public String getNewNoteFilePath(String noteName) {
        return DIRECTORY_PATH + noteName + ".txt";
    }

    // Save the note metadata (name, date, and time) to the metadata file
    public void saveNoteMetadata(String noteName, String dateTime) {
        try (FileWriter writer = new FileWriter(METADATA_FILE, true)) {
            writer.write(noteName + " | " + dateTime + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to the metadata file.");
            e.printStackTrace();
        }
    }
}
