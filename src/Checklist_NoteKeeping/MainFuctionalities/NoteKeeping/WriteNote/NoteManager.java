package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.WriteNote;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    private static final String DIRECTORY_PATH = "notes/";

    public NoteManager() {
        File dir = new File(DIRECTORY_PATH);
        if (!dir.exists()) {
            dir.mkdir();  
        }
    }

    // list of all the saved notes
    public List<String> getNotes() {
        File dir = new File(DIRECTORY_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        List<String> notes = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                notes.add(file.getName());
            }
        }
        return notes;
    }

    // path for a new note
    public String getNewNoteFilePath(String noteName) {
        return DIRECTORY_PATH + noteName + ".txt";
    }
}
