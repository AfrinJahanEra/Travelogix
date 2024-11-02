package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.VoiceNote;

import javax.sound.sampled.LineUnavailableException;
import java.util.Scanner;

public class AudioRecorder {

    private VoiceNoteManager voiceNoteManager;

    public AudioRecorder(VoiceNoteManager voiceNoteManager) {
        this.voiceNoteManager = voiceNoteManager;
    }

    public void captureAndSaveAudio() {

        try {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name for the voice note: ");
            String noteName = scanner.nextLine();

            String filePath = voiceNoteManager.getNewVoiceNoteFilePath(noteName);

            byte[] audioBytes = AudioUtils.captureAudio(10000);
            System.out.println("Audio captured of length: " + audioBytes.length);

            AudioUtils.saveAudioToFile(audioBytes, AudioUtils.getAudioFormat(), filePath);
            
        } 
        
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
