package Test;


import org.junit.jupiter.api.Test;

import Traveler.Past_Travel_History.AlarmHistoryManager;


import java.io.ByteArrayOutputStream;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlarmHistoryManagerTest {

    private static final String TEST_ALARM_HISTORY_FILE = "src\\TXT_Files\\alarm_history.txt";

    @Test
    public void testViewAlarmHistory() throws Exception {
  
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method
        AlarmHistoryManager.viewAlarmHistory();

        // Restore System.out
        System.setOut(System.out);

        // Verify the output
        String output = outContent.toString();
        assertTrue(output.contains("2022-10-20 09:00:00")); // First alarm should be displayed
        assertTrue(output.contains("ww")); // First alarm message should be displayed
        assertTrue(output.contains("2024-10-09 09:00:99")); // Second alarm should be displayed
        assertTrue(output.contains("ee")); // Second alarm message should be displayed

    }

}