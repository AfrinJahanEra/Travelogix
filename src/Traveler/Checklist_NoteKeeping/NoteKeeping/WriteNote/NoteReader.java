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
        List<String> notes = noteManager.getNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
            return;
        }

        System.out.println("\nAvailable Notes:");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        int noteNumber = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Choose a note number to read: ");
            try {
                noteNumber = Integer.parseInt(scanner.nextLine());
                if (noteNumber >= 1 && noteNumber <= notes.size()) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please select a number between 1 and " + notes.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        String selectedNote = notes.get(noteNumber - 1);
        File noteFile = new File("notes/" + selectedNote);
        readNoteFromFile(noteFile);
    }

    // Read a note from a file and print its content
    private void readNoteFromFile(File noteFile) {
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
