package Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote;

import java.util.List;
import java.util.Scanner;

public class VoiceNoteDashBoard {

    public void voiceNoteDashBoard() {
        VoiceNoteManager voiceNoteManager = new VoiceNoteManager();
        AudioRecorder recorder = new AudioRecorder(voiceNoteManager);
        AudioPlayer player = new AudioPlayer();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║                VOICE NOTES               ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║                                          ║");
            System.out.println("║    [1] Add Voice Note                    ║");
            System.out.println("║    [2] Listen to Voice Note              ║");
            System.out.println("║    [3] Delete Voice Note                 ║");
            System.out.println("║    [4] Go back (Exit)                    ║");
            System.out.println("╚══════════════════════════════════════════╝");
        
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> recorder.captureAndSaveAudio();
                case 2 -> {
                    List<String> voiceNotes = voiceNoteManager.getVoiceNotes();
                    if (voiceNotes.isEmpty()) {
                        System.out.println("No voice notes available.");
                    } else {
                        for (int i = 0; i < voiceNotes.size(); i++) {
                            System.out.println((i + 1) + ". " + voiceNotes.get(i));
                        }
                        System.out.print("Choose a voice note number to play: ");
                        int noteNumber = scanner.nextInt();
                        if (noteNumber >= 1 && noteNumber <= voiceNotes.size()) {
                            String filePath = "voice_notes/" + voiceNotes.get(noteNumber - 1);
                            player.playVoiceNote(filePath);
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                }
                case 3 -> {
                    List<String> voiceNotes = voiceNoteManager.getVoiceNotes();
                    if (voiceNotes.isEmpty()) {
                        System.out.println("No voice notes available to delete.");
                    } else {
                        for (int i = 0; i < voiceNotes.size(); i++) {
                            System.out.println((i + 1) + ". " + voiceNotes.get(i));
                        }
                        System.out.print("Choose a voice note number to delete: ");
                        int noteNumber = scanner.nextInt();
                        if (noteNumber >= 1 && noteNumber <= voiceNotes.size()) {
                            voiceNoteManager.deleteVoiceNote(voiceNotes.get(noteNumber - 1));
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Returning to main dashboard...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
