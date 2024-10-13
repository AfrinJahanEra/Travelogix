package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.VoiceNote;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

   
    public void playVoiceNote(String filePath) {
        File audioFile = new File(filePath);
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
            audioLine.open(format);
            audioLine.start();
            System.out.println("Playing audio...");

            

            audioLine.drain();
            audioLine.close();
            System.out.println("Playback completed.");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }
}
