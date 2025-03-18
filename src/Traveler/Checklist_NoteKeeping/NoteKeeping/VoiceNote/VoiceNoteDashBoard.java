package Traveler.Checklist_NoteKeeping.NoteKeeping.VoiceNote;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VoiceNoteDashBoard {

    private final Scanner scanner = new Scanner(System.in);

    public void voiceNoteDashBoard() {
        VoiceNoteManager voiceNoteManager = new VoiceNoteManager();
        AudioRecorder recorder = new AudioRecorder(voiceNoteManager);
        AudioPlayer player = new AudioPlayer();

        while (true) {
            waitForEnterKey();
            clearTerminal();

            System.out.println("\n                                              ╔══════════════════════════════════════════╗");
            System.out.println("                                              ║                VOICE NOTES               ║");
            System.out.println("                                              ╠══════════════════════════════════════════╣");
            System.out.println("                                              ║                                          ║");
            System.out.println("                                              ║    [1] Add Voice Note                    ║");
            System.out.println("                                              ║    [2] Listen to Voice Note              ║");
            System.out.println("                                              ║    [3] Delete Voice Note                 ║");
            System.out.println("                                              ║    [4] Go back (Exit)                    ║");
            System.out.println("                                              ╚══════════════════════════════════════════╝");

            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            scanner.nextLine();

            switch (choice) {
                case 1 -> recorder.captureAndSaveAudio();
                case 2 -> {
                    List<String[]> voiceNotes = voiceNoteManager.getSortedVoiceNotesByDate();
                    if (voiceNotes.isEmpty()) {
                        System.out.println("No voice notes available.");
                    } else {
                        System.out.printf("%-5s %-30s %-15s %-10s%n", "No.", "Voice Note Name", "Date", "Time");
                        System.out.println("═══════════════════════════════════════════════════════════════");
                        for (int i = 0; i < voiceNotes.size(); i++) {
                            System.out.printf("%-5d %-30s %-15s %-10s%n", i + 1, voiceNotes.get(i)[0], voiceNotes.get(i)[1], voiceNotes.get(i)[2]);
                        }

                        int noteNumber = -1;
                        while (true) {
                            try {
                                System.out.print("Choose a voice note number to play: ");
                                noteNumber = Integer.parseInt(scanner.nextLine());
                                if (noteNumber >= 1 && noteNumber <= voiceNotes.size()) {
                                    break;
                                } else {
                                    System.out.println("Invalid choice. Please select a valid number.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a number.");
                            }
                        }
                        String filePath = "voice_notes/" + voiceNotes.get(noteNumber - 1)[0];
                        player.playVoiceNote(filePath);
                    }
                }
                case 3 -> {
                    List<String[]> voiceNotes = voiceNoteManager.getSortedVoiceNotesByDate();
                    if (voiceNotes.isEmpty()) {
                        System.out.println("No voice notes available to delete.");
                    } else {
                        System.out.printf("%-5s %-30s %-15s %-10s%n", "No.", "Voice Note Name", "Date", "Time");
                        System.out.println("═══════════════════════════════════════════════════════════════");
                        for (int i = 0; i < voiceNotes.size(); i++) {
                            System.out.printf("%-5d %-30s %-15s %-10s%n", i + 1, voiceNotes.get(i)[0], voiceNotes.get(i)[1], voiceNotes.get(i)[2]);
                        }

                        int noteNumber = -1;
                        while (true) {
                            try {
                                System.out.print("Choose a voice note number to delete: ");
                                noteNumber = Integer.parseInt(scanner.nextLine());
                                if (noteNumber >= 1 && noteNumber <= voiceNotes.size()) {
                                    break;
                                } else {
                                    System.out.println("Invalid choice. Please select a valid number.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a number.");
                            }
                        }
                        voiceNoteManager.deleteVoiceNote(voiceNotes.get(noteNumber - 1)[0]);
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

    private int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine();
            }
        }
    }

    private void waitForEnterKey() {
        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    private void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
