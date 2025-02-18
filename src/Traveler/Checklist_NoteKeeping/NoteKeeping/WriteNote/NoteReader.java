package Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class NoteReader {

    private NoteManager noteManager;

    public NoteReader(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    public void showAndReadNote() {
        try {
            List<String[]> notes = noteManager.getNotes();

            if (notes.isEmpty()) {
                System.out.println("No notes available.");
                return;
            }

            System.out.println("\nNo.   Note Name                     Date            Time");
            System.out.println("═══════════════════════════════════════════════════════════");

            for (int i = 0; i < notes.size(); i++) {
                System.out.printf("%-5d %-30s %-15s %-10s%n", i + 1, notes.get(i)[0], notes.get(i)[1], notes.get(i)[2]);
            }

            int noteNumber = getNoteNumber(notes.size());
            File noteFile = new File("notes/" + notes.get(noteNumber - 1)[0]);
            readNoteFromFile(noteFile);

        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
            e.printStackTrace();
        }
    }

    private int getNoteNumber(int maxNumber) {
        Scanner scanner = new Scanner(System.in);
        int noteNumber;

        while (true) {
            try {
                System.out.print("Choose a note number to read: ");
                noteNumber = Integer.parseInt(scanner.nextLine());

                if (noteNumber >= 1 && noteNumber <= maxNumber) {
                    return noteNumber;
                } else {
                    System.out.println("Invalid choice. Please select a number between 1 and " + maxNumber + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void readNoteFromFile(File noteFile) {
        if (!noteFile.exists() || !noteFile.isFile()) {
            System.out.println("The selected note file does not exist or is not a valid file.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(noteFile.toPath());
            System.out.println("\n--- Note Content ---");
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the note.");
            e.printStackTrace();
        }
    }
}
