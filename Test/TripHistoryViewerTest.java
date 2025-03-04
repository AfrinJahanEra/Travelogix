package Test;



import org.junit.jupiter.api.Test;

import Traveler.Past_Travel_History.TripHistoryViewer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripHistoryViewerTest{

    private static final String TEST_TRIP_FILE = "src/TXT_Files/trips.txt";

    @Test
    public void testViewPastTrips() throws Exception {
   
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

  
        TripHistoryViewer.viewPastTrips();

        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("Mymensingh")); 
        assertTrue(!output.contains("cumilla"));


    }


}