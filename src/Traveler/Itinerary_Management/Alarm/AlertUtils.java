package Itinerary_Management.Alarm;

import java.util.Date;

public class AlertUtils {
    public static void waitForAlert(Date alertDate, String message) {
        while (true) {
            Date currentDate = new Date();
            if (currentDate.after(alertDate)) {
                System.out.println(message);
                break;
            }
            try {
                Thread.sleep(1000); // Check every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
