package Traveler.Itinerary_Management.Alarm;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundUtils {
    public static void playSound(String soundFilePath) {
        try {
            File soundFile = new File("C:\\Users\\afrin\\OneDrive\\Desktop\\TravelApp\\src\\Traveler\\Itinerary_Management\\Alarm\\sparcle.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000); // Wait for the sound to finish
            clip.close();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }
}
