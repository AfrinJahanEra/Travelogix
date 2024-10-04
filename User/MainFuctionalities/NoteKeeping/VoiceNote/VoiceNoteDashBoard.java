package User.MainFuctionalities.NoteKeeping.VoiceNote;

import java.util.List;
import java.util.Scanner;

public class VoiceNoteDashBoard {
    public void voiceNoteDashBoard(){
    VoiceNoteManager voiceNoteManager = new VoiceNoteManager();
    AudioRecorder recorder = new AudioRecorder(voiceNoteManager);
    AudioPlayer player = new AudioPlayer();

    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("1. Add Voice Note");
        System.out.println("2. Listen to Voice Note");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  

        switch (choice) {
            case 1:
                recorder.captureAndSaveAudio();
                break;
            case 2:
                List<String> voiceNotes = voiceNoteManager.getVoiceNotes();
                if (voiceNotes.isEmpty()) {
                    System.out.println("No voice notes available.");
                } else {
                    System.out.println("\nAvailable Voice Notes:");
                    for (int i = 0; i < voiceNotes.size(); i++) {
                        System.out.println((i + 1) + ". " + voiceNotes.get(i));
                    }
                    System.out.print("Choose a voice note number to play: ");
                    int noteNumber = scanner.nextInt();
                    if (noteNumber < 1 || noteNumber > voiceNotes.size()) {
                        System.out.println("Invalid choice.");
                    } else {
                        String selectedNote = voiceNotes.get(noteNumber - 1);
                        String filePath = "voice_notes/" + selectedNote;
                        player.playVoiceNote(filePath);
                    }
                }
                break;
            case 3:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
}

