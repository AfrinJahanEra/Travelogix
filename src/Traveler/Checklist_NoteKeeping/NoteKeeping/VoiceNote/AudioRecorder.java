package Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote;

import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;

public class AudioRecorder {

    private final VoiceNoteManager voiceNoteManager;
    private volatile boolean isRecording = true;

    public AudioRecorder(VoiceNoteManager voiceNoteManager) {
        this.voiceNoteManager = voiceNoteManager;
    }

    public void captureAndSaveAudio() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name for the voice note: ");
            String noteName = scanner.nextLine();

            String filePath = voiceNoteManager.getNewVoiceNoteFilePath(noteName);

            // Start a thread to listen for manual stop
            Thread stopThread = new Thread(() -> {
                System.out.println("Press ENTER to stop recording...");
                scanner.nextLine(); // Wait for user input
                isRecording = false; // Stop the recording
            });
            stopThread.start();

            // Capture audio indefinitely until user stops it
            byte[] audioBytes = AudioUtils.captureAudio(this);
            System.out.println("Audio captured of length: " + audioBytes.length);

            AudioUtils.saveAudioToFile(audioBytes, AudioUtils.getAudioFormat(), filePath);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public boolean isRecording() {
        return isRecording;
    }
}