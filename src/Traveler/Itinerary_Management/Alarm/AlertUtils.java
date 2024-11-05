package Traveler.Itinerary_Management.Alarm;

import java.util.Date;

public class AlertUtils {
    public static void waitForAlert(Date alertDate) {
        while (true) {
            Date currentDate = new Date();
            if (currentDate.after(alertDate)) {
                break;
            }
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}