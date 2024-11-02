package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.WriteNote;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class NoteDeleter {

    private NoteManager noteManager;

    public NoteDeleter(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    public void deleteNote() {
        List<String> notes = noteManager.getNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes available to delete.");
            return;
        }

        System.out.println("\nAvailable Notes:");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a note number to delete: ");
        int noteNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (noteNumber < 1 || noteNumber > notes.size()) {
            System.out.println("Invalid choice.");
        } else {
            String selectedNote = notes.get(noteNumber - 1);
            File noteFile = new File("notes/" + selectedNote);
            if (noteFile.delete()) {
                System.out.println("Note '" + selectedNote + "' deleted successfully.");
            } else {
                System.out.println("Error deleting the note.");
            }
        }
    }
}
