package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.VoiceNote;

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

        
    }
}
}

