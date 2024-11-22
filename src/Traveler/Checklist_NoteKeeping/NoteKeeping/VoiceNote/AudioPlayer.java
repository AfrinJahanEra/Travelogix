package Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioPlayer {

    public void playVoiceNote(String filePath) {
        File audioFile = new File(filePath);
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            try (SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info)) {
                audioLine.open(format);
                audioLine.start();
                System.out.println("Playing audio...");

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = audioStream.read(buffer)) != -1) {
                    audioLine.write(buffer, 0, bytesRead);
                }

                audioLine.drain();
                System.out.println("Playback completed.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }
}
