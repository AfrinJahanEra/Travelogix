package Traveler.Itinerary_Management.Alarm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AlertSystem {
    private static final String SOUND_FILE_PATH = "src\\Traveler\\Itinerary_Management\\Alarm\\sparcle.wav";
    private static final String HISTORY_FILE = "alarm_history.txt";

    public void alertSystem() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for date, time, and message
        System.out.print("Enter reminder date and time (yyyy-MM-dd HH:mm:ss): ");
        String inputDateTime = scanner.nextLine();

        System.out.print("Enter reminder message: ");
        String message = scanner.nextLine();

        // Parse date and time
        Date alertDate = DateUtils.parseDateTime(inputDateTime);

        if (alertDate != null) {
            System.out.println("Reminder set for: " + inputDateTime);
            saveToHistory(inputDateTime, message);
            AlertUtils.waitForAlert(alertDate, message);
            SoundUtils.playSound(SOUND_FILE_PATH); // Play sound when time is reached
        } else {
            System.out.println("Invalid date and time format. Please use yyyy-MM-dd HH:mm:ss.");
        }
    }

    private void saveToHistory(String dateTime, String message) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(HISTORY_FILE));
            int lastNumber = lines.isEmpty() ? 0 : lines.size();
            int newNumber = lastNumber + 1;

            try (PrintWriter writer = new PrintWriter(new FileWriter(HISTORY_FILE, true))) {
                writer.println(newNumber + ". " + dateTime + " - " + message);
            }
        } catch (IOException e) {
            System.out.println("Error writing to history file: " + e.getMessage());
        }
    }
}
