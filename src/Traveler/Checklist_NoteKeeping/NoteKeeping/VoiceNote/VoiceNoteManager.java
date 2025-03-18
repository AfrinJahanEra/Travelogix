package Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class VoiceNoteManager {

    private static final String DIRECTORY_PATH = "voice_notes/";

    public VoiceNoteManager() {
        File dir = new File(DIRECTORY_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public List<String[]> getSortedVoiceNotesByDate() {
        File dir = new File(DIRECTORY_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".wav"));
        List<String[]> voiceNotes = new ArrayList<>();
        if (files != null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

            for (File file : files) {
                String fileName = file.getName();
                Date lastModified = new Date(file.lastModified());
                String date = dateFormatter.format(lastModified);
                String time = timeFormatter.format(lastModified);
                voiceNotes.add(new String[]{fileName, date, time});
            }

            
            voiceNotes.sort((a, b) -> {
                int dateComparison = b[1].compareTo(a[1]);
                return dateComparison != 0 ? dateComparison : b[2].compareTo(a[2]); 
            });
        }
        return voiceNotes;
    }

    public String getNewVoiceNoteFilePath(String noteName) {
        return DIRECTORY_PATH + noteName + ".wav";
    }

    public void deleteVoiceNote(String fileName) {
        File file = new File(DIRECTORY_PATH + fileName);
        if (file.delete()) {
            System.out.println("Voice note '" + fileName + "' deleted successfully.");
        } else {
            System.out.println("Failed to delete voice note '" + fileName + "'.");
        }
    }
}
