package Itinerary_Management.Alarm;

import java.util.Date;
import java.util.Scanner;

public class AlertSystem {

    private static final String SOUND_FILE_PATH = "alert-85101.mp3";

    public void alertSystem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter alert date and time (yyyy-MM-dd HH:mm:ss): ");
        String inputDateTime = scanner.nextLine();

        Date alertDate = DateUtils.parseDateTime(inputDateTime);

        if (alertDate != null) {
            System.out.println("Alert set for: " + inputDateTime);
            AlertUtils.waitForAlert(alertDate, "Time for your activity!");
            SoundUtils.playSound(SOUND_FILE_PATH);
        } else {
            System.out.println("Invalid date and time format.");
        }

        scanner.close();
    }
}
