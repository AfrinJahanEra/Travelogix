package Traveler.Past_Travel_History;


import java.io.*;
import java.text.SimpleDateFormat;

public class AlarmHistoryManager {
    private static final String ALARM_HISTORY_FILE = "src\\TXT_Files\\alarm_history.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void logAlarm(String dateTime, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ALARM_HISTORY_FILE, true))) {
            writer.write(dateTime + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to alarm history file: " + e.getMessage());
        }
    }
}