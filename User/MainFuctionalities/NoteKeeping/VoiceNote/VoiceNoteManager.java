package User.MainFuctionalities.NoteKeeping.VoiceNote;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VoiceNoteManager {

    private static final String DIRECTORY_PATH = "voice_notes/";

    public VoiceNoteManager() {
        File dir = new File(DIRECTORY_PATH);
        if (!dir.exists()) {
            dir.mkdir();  
        }
    }

    // Method to get all voice notes
    public List<String> getVoiceNotes() {
        File dir = new File(DIRECTORY_PATH);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".wav"));
        List<String> voiceNotes = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                voiceNotes.add(file.getName());
            }
        }
        return voiceNotes;
    }

    // Generating a file path for a new voice note
    public String getNewVoiceNoteFilePath(String noteName) {
        return DIRECTORY_PATH + noteName + ".wav";
    }
}
