package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.VoiceNote;

import javax.sound.sampled.*;
import java.io.*;

public class AudioUtils {

    // default audio format
    public static AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public static void saveAudioToFile(byte[] audioBytes, AudioFormat format, String filePath) {
        
    }

    // Capturing audio for a given duration (in milliseconds)
    public static byte[] captureAudio(int durationMs) throws LineUnavailableException {
        AudioFormat format = getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            throw new LineUnavailableException("Line not supported");
        }

        TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
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
        line.close();
        System.out.println("Recording stopped");

        return out.toByteArray();
    }
}
