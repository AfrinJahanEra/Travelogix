package Traveler.Itinerary_Management.Alarm;

import java.util.Date;
import java.util.Scanner;

public class AlertSystem {
    private static final String SOUND_FILE_PATH = "src\\Traveler\\Itinerary_Management\\Alarm\\sparcle.wav";

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
            AlertUtils.waitForAlert(alertDate, message);
            SoundUtils.playSound(SOUND_FILE_PATH); // Play sound when time is reached
        } else {
            System.out.println("Invalid date and time format. Please use yyyy-MM-dd HH:mm:ss.");
        }
    }
}
