package Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

    public List<String[]> getNotes(){
        File dir = new File(DIRECTORY_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        List<String[]> notes = new ArrayList<>();

        if (files != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            for (File file : files) {
                try {
                    String name = file.getName();
                    Date lastModified = new Date(file.lastModified());
                    String date = dateFormat.format(lastModified);
                    String time = timeFormat.format(lastModified);
                    notes.add(new String[]{name, date, time});
                } catch (Exception e) {
                    System.out.println("Error processing note: " + file.getName());
                }
            }

            notes.sort((a, b) -> {
                int dateComparison = b[1].compareTo(a[1]); 
                return dateComparison != 0 ? dateComparison : b[2].compareTo(a[2]); 
            });
        }

        return notes;
    }

    public String getNewNoteFilePath(String noteName) {
        return DIRECTORY_PATH + noteName + ".txt";
    }
}
