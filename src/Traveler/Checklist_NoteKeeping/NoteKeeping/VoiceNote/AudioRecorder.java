package Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote;

import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;

public class AudioRecorder {

    private final VoiceNoteManager voiceNoteManager;

    public AudioRecorder(VoiceNoteManager voiceNoteManager) {
        this.voiceNoteManager = voiceNoteManager;
    }

    public void captureAndSaveAudio() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name for the voice note: ");
            String noteName = scanner.nextLine();

            String filePath = voiceNoteManager.getNewVoiceNoteFilePath(noteName);
            byte[] audioBytes = AudioUtils.captureAudio(3000);
            System.out.println("Audio captured of length: " + audioBytes.length);

            AudioUtils.saveAudioToFile(audioBytes, AudioUtils.getAudioFormat(), filePath);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
