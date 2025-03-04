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

    public static void viewAlarmHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ALARM_HISTORY_FILE))) {
            String line;
            int index = 1;
            
            System.out.println("══════════════════════════════════════════════");
            System.out.println("║ Alarm History                              ║");
            System.out.println("══════════════════════════════════════════════");
            System.out.println("║ No. ║ Date & Time            ║ Message     ║");
            System.out.println("══════════════════════════════════════════════");
            
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                found = true;
                String[] alarmData = line.split(" - ", 2);
                System.out.printf("║ %-3d ║ %-20s ║ %-10s ║\n", index++, alarmData[0], alarmData[1]);
            }
            
            if (!found) {
                System.out.println("║ No alarms set yet.                          ║");
            }
            
            System.out.println("══════════════════════════════════════════════");
        } catch (IOException e) {
            System.out.println("Error reading alarm history file: " + e.getMessage());
        }
    }
}