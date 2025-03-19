package Traveler.Checklist_NoteKeeping.NoteKeeping.WriteNote;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class NoteDeleter {

    private NoteManager noteManager;

    public NoteDeleter(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    public void deleteNote() {
        try {
            List<String[]> notes = noteManager.getNotes();
            if (notes.isEmpty()) {
                System.out.println("No notes available to delete.");
                return;
            }

            System.out.println("\nNo.   Note Name                     Date            Time");
            System.out.println("═══════════════════════════════════════════════════════════");

            for (int i = 0; i < notes.size(); i++) {
                System.out.printf("%-5d %-30s %-15s %-10s%n", i + 1, notes.get(i)[0], notes.get(i)[1], notes.get(i)[2]);
            }

            int noteNumber = getNoteNumber(notes.size());
            String[] selectedNote = notes.get(noteNumber - 1);
            File noteFile = new File("notes/" + selectedNote[0]); 

            if (noteFile.exists() && noteFile.delete()) {
                System.out.println("Note '" + selectedNote[0] + "' deleted successfully.");
                notes.remove(noteNumber - 1); 
            } else {
                System.out.println("Error deleting the note. Make sure the file exists and is not locked.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred while trying to delete the note.");
            e.printStackTrace();
        }
    }

    private int getNoteNumber(int maxNumber) {
        Scanner scanner = new Scanner(System.in);
        int noteNumber;

        while (true) {
            System.out.print("Choose a note number to delete: ");
            try {
                noteNumber = Integer.parseInt(scanner.nextLine());

                if (noteNumber >= 1 && noteNumber <= maxNumber) {
                    return noteNumber;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + maxNumber + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
