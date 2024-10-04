package Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.WriteNote;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NoteWriter {

    private NoteManager noteManager;

    public NoteWriter(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

    // Capture and save a note
    public void writeAndSaveNote() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the note: ");
        String noteName = scanner.nextLine();
        String filePath = noteManager.getNewNoteFilePath(noteName);

        System.out.println("Start writing your note (enter 'DONE' on a new line when finished):");

        StringBuilder noteContent = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals("DONE")) {
            noteContent.append(line).append(System.lineSeparator());
        }

        // Save the note content to a file
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(noteContent.toString());
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving the note.");
            e.printStackTrace();
        }
    }
}
