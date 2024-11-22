package Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote;

import java.io.*;
import javax.sound.sampled.*;

public class AudioUtils {

    public static AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public static void saveAudioToFile(byte[] audioBytes, AudioFormat format, String filePath) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
             AudioInputStream ais = new AudioInputStream(bais, format, audioBytes.length / format.getFrameSize())) {
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, new File(filePath));
            System.out.println("Audio saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] captureAudio(int durationMs) throws LineUnavailableException {
        AudioFormat format = getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        try (TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info)) {
            line.open(format);
            line.start();
            System.out.println("Start capturing...");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            long end = System.currentTimeMillis() + durationMs;

            while (System.currentTimeMillis() < end) {
                int bytesRead = line.read(buffer, 0, buffer.length);
                out.write(buffer, 0, bytesRead);
            }

            line.stop();
            System.out.println("Recording stopped");
            return out.toByteArray();
        }
    }
}
